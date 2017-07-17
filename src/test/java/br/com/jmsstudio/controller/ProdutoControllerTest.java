package br.com.jmsstudio.controller;

import br.com.jmsstudio.config.AppConfig;
import br.com.jmsstudio.config.DataSourceConfigTest;
import br.com.jmsstudio.config.JPAConfig;
import br.com.jmsstudio.config.SecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, SecurityConfig.class, JPAConfig.class, DataSourceConfigTest.class})
@ActiveProfiles("test")
public class ProdutoControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    public void somentePapelAdminDeveAcessarFormProdutos() throws Exception {
        SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor defaultUser =
                SecurityMockMvcRequestPostProcessors.user("user@user.com").password("12345").roles("USER");

        SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor adminUser =
                SecurityMockMvcRequestPostProcessors.user("admin@user.com").password("12345").roles("ADMIN");

        mockMvc.perform(MockMvcRequestBuilders.get("/produto/form").with(defaultUser))
            .andExpect(MockMvcResultMatchers.status().is(403));

        mockMvc.perform(MockMvcRequestBuilders.get("/produto/form").with(adminUser))
            .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
