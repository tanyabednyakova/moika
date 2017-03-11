package io.khasang.moika.config_for_test_purposes;

import io.khasang.moika.config.AppConfig;

import io.khasang.moika.service.impl.RostislavDataAccessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author Rostislav Dublin
 * @since Конфигурация для юнит-тестирования, не подключающая ничего лишнего.
 */
@Configuration
//@EnableCaching
@ComponentScan(
        basePackages = {
                "io.khasang.moika.config",
                "io.khasang.moika.dao",
                "io.khasang.moika.service",
                "io.khasang.moika.validator",
                "io.khasang.moika.util"
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "io.khasang.moika.config.application.*"),
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                        RostislavDataAccessServiceImpl.class,
                       // AKovalevDataAccessServiceImpl.class,
                        AppConfig.class})
        })
//@EnableCaching
@PropertySource(value = {"classpath:util.properties", "classpath:auth.properties"})
public class TestAppConfig {
    final private Environment environment;

    @Autowired
    public TestAppConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public Environment getEnvironment() {
        return environment;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.postgresql.driverClass"));
        dataSource.setUrl(environment.getProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
/*
    @Bean
    public CreateTable createTable() {
        return new CreateTable(jdbcTemplate());
    }

    @Bean
    public MadvDataAcces madvDataAcces() {
        return new MadvDataAccesImpl(jdbcTemplate());
    }

    @Bean
    public MadvDataAccesService madvDataAccesService() {
        return new MadvDataAccesServiceImpl(madvDataAcces());
    }

 DRS 2017-03-01 см. новую реализацию (с опорой на сущности User и Role) в классе UserDetailsServiceImpl.
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource());
        jdbcImpl.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcImpl;
    }


    @Bean
    public PskvorDataAccess pskvorDataAccess() {
        return new PskvorDataAccessJdbcImpl(jdbcTemplate());
    }

    @Bean
    public PskvorDataAccessService pskvorDataAccessService() {
        return new PskvorDataAccessService(pskvorDataAccess());
    }

    @Bean
    public CompanyService companyService() {
        return new CompanyServiceImpl();
    }
    */
}
