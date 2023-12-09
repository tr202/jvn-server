package ru.jvn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import org.springframework.core.env.Environment;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.sql.DataSource;
import java.util.Properties;

@Configuration
//@EnableJpaRepositories
@EnableTransactionManagement
@EnableWebMvc

//@EnableSpringConfigured


@ComponentScan(basePackages = {
        "ru.jvn.controller",
        "ru.jvn.repository",
})

public class SpringConfig implements
        WebMvcConfigurer {

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationContext ctx;

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        StandardServletMultipartResolver ssmpr = new StandardServletMultipartResolver();
        return ssmpr;
    }

    @Bean
    public DataSource MyConnectionProviderImpl() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://db:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(MyConnectionProviderImpl());
        sessionFactory.setPackagesToScan("ru.jvn.model");
        sessionFactory.setHibernateProperties(hibernateProperties());
        System.out.println("Heloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        return sessionFactory;
    }


    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
                "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");


        return hibernateProperties;
    }

}
