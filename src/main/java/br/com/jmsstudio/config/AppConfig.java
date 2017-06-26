package br.com.jmsstudio.config;

import br.com.jmsstudio.controller.HomeController;
import br.com.jmsstudio.infra.FileManager;
import br.com.jmsstudio.model.CarrinhoCompras;
import br.com.jmsstudio.repository.ProdutoRepository;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.concurrent.TimeUnit;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = {HomeController.class, ProdutoRepository.class, FileManager.class, CarrinhoCompras.class})
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposedContextBeanNames("carrinhoCompras");
        return resolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("/WEB-INF/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(1);

        return messageSource;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CacheManager cacheManager() {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.expireAfterAccess(5, TimeUnit.MINUTES).maximumSize(100);

        GuavaCacheManager manager = new GuavaCacheManager();
        manager.setCacheBuilder(cacheBuilder);
        return manager;
    }

//    @Bean
//    public FormattingConversionService mvcConversionService() {
//        FormattingConversionService conversionService = new FormattingConversionService();
//
//        DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
//        formatterRegistrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
//
//        formatterRegistrar.registerFormatters(conversionService);
//
//        return conversionService;
//    }
}
