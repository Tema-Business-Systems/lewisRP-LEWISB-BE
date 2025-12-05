package com.transport.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(
                                "http://localhost:3000",
                                "https://localhost:3000",
                                "https://localhost:8048",
                                "https://localhost:8049",
                                "https://192.168.1.211:8048",
                                "https://192.168.1.211:8049",
                                "https://solutions.tema-systems.com:8048",
                                "https://solutions.tema-systems.com:8049",
                                "http://solutions.tema-systems.com:8048",
                                "http://solutions.tema-systems.com:8049"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}