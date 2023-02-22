package com.example.apigateway.config.mvc;

import com.example.apigateway.config.filters.ExceptionHandlerFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final String allowedOrigin;

    public MvcConfig(@Value("${security.allowed-origin}") final String allowedOrigin) {
        this.allowedOrigin = allowedOrigin;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowCredentials(true)
            .allowedOrigins(allowedOrigin)
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE");
    }
}
