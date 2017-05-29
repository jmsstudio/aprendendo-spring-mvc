package br.com.jmsstudio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@EnableTransactionManagement
public class JPAConfig {

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("hibernate");
        dataSource.setPassword("12345");
        dataSource.setUrl("jdbc:postgresql://localhost/spring-mvc");
        dataSource.setDriverClassName("org.postgresql.Driver");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");


        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaProperties(properties);
        factoryBean.setPackagesToScan("br.com.jmsstudio.model");

        return factoryBean;
    }
}
