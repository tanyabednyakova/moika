package io.khasang.moika.config;

import io.khasang.moika.annotation.AddMenuPathAnnotationBeanPostProcessor;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.model.PskvorDataAccess;
import io.khasang.moika.model.PskvorDataAccessJdbcImpl;
import io.khasang.moika.service.CompanyService;
import io.khasang.moika.service.PskvorDataAccessService;
import io.khasang.moika.service.impl.CompanyServiceImpl;
import io.khasang.moika.service.impl.PskvorDataAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
//@EnableCaching
@PropertySource(value = {"classpath:util.properties"})
public class AppConfig {
    final private Environment environment;

    @Autowired
    public AppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Environment getEnvironment() {
        return environment;
    }

    @Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driverClass"));
        dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public CreateTable createTable(){
        return  new CreateTable(jdbcTemplate());
    }

}
