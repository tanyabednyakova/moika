package io.khasang.moika.config;

import io.khasang.moika.model.CreateTable;
import io.khasang.moika.model.PskvorDataAccess;
import io.khasang.moika.model.PskvorDataAccessJdbcImpl;
import io.khasang.moika.service.CompanyService;
import io.khasang.moika.service.PskvorDataAccessService;
import io.khasang.moika.service.QueueService;
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
@PropertySource(value = {"classpath:util.properties", "classpath:auth.properties"})
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

  /*  @Bean
    public MadvDataAcces madvDataAcces(){return new MadvDataAccesImpl(jdbcTemplate());}
    @Bean
    public MadvDataAccesService madvDataAccesService(){return new MadvDataAccesServiceImpl(madvDataAcces());}

  DRS 2017-03-01 см. новую реализацию (с опорой на сущности User и Role) в классе UserDetailsServiceImpl.
    @Bean
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource());
        jdbcImpl.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        jdbcImpl.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        return jdbcImpl;
    }
*/

    @Bean
    public PskvorDataAccess pskvorDataAccess(){
         return new PskvorDataAccessJdbcImpl(jdbcTemplate());
    }

    @Bean
    public TatyanaDataAccess tatyanaDataAccess(){
        return new TatyanaDataAccessImp(jdbcTemplate());
    }

    @Bean
    public PskvorDataAccessService pskvorDataAccessService() {
        return new PskvorDataAccessServiceImpl();
    }

    @Bean
    public CompanyService companyService() { return new CompanyServiceImpl();}

    /**
     * Валидатор для работы с анотациями, согласно спецификации jsr 303, подробности по ссылке
     * https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#chapter-bean-constraints

    @Bean(name = "jsr303Validator")
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }*/
}
