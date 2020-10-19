package com.riviewz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class Config {

    // declare the aspect itself as a bean
    @Bean
    public ControllerAdvices controllerAdvices() {
        return new ControllerAdvices();
    }

    @Bean
    public ServiceAdvices serviceAdvices() {
        return new ServiceAdvices();
    }

    @Bean
    public DaoAdvices daoAdvices() {
        return new DaoAdvices();
    }
}