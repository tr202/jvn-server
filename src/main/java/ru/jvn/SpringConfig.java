package ru.jvn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import org.springframework.core.env.Environment;
import org.springframework.core.task.TaskExecutor;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


import javax.sql.DataSource;
import java.security.Security;
import java.time.Clock;
import java.time.Duration;
import java.util.*;

@Configuration
@EnableWebMvc

//@EnableSpringConfigured


@ComponentScan(basePackages = {
    "ru.jvn.controller",
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
}
