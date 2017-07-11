package br.com.jmsstudio.config;

import br.com.jmsstudio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/produto/form").hasRole("ADMIN")
                    .antMatchers("/carrinho/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/produto").hasRole("ADMIN")
                    .antMatchers("/produto/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/resources/**").permitAll() //libera o carregamento de css
                    .anyRequest().authenticated()
                    .and().formLogin()
                        .loginPage("/login").permitAll()
                    .and().logout()
                        .logoutSuccessUrl("/")
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioRepository).passwordEncoder(new ShaPasswordEncoder());
    }
}
