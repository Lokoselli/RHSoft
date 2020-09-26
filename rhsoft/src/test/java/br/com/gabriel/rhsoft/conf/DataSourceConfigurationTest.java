package br.com.gabriel.rhsoft.conf;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {

    @Bean
    @Profile("test")
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/test");
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("gitpod");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    @Profile("test")
    public Properties aditionalProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        props.setProperty("hibernate.show_sql", "false");
        props.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        return props;
    }

}