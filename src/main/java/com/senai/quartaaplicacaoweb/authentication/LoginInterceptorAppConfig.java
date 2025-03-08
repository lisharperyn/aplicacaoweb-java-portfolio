package com.senai.quartaaplicacaoweb.authentication;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LoginInterceptorAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor( new LoginInterceptor())
                .excludePathPatterns(
                        "/error",
                        "/vendor/**",
                        "/login",
                        "/logar",
                        "/img/**",
                        "/js/**",
                        "/favicon.ico",
                        "/css/**"
                );
    }
}
