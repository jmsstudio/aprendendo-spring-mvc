package br.com.jmsstudio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

@Profile("prod")
public class JPAProdConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() throws URISyntaxException {

        URI databaseUrl = new URI(environment.getProperty("DATABASE_URL"));

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(databaseUrl.getUserInfo().split(":")[0]);
        dataSource.setPassword(databaseUrl.getUserInfo().split(":")[1]);
        dataSource.setUrl("jdbc:postgresql://" + databaseUrl.getHost() + ":" + databaseUrl.getPort() + databaseUrl.getPath());
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public Properties aditionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

}
