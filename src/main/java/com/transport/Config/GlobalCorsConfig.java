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
                                "http://localhost:8048",
                                "http://localhost:8049",
                                "http://tmssolutions.tema-systems.com:8049",
                                "http://tmssolutions.tema-systems.com:8048",
                                "https://tmssolutions.tema-systems.com:8049",
                                "https://tmssolutions.tema-systems.com:8048",
                                "http://tmssolutions.tema-systems.com:8050",
                                "http://192.168.0.181:3000",
                                "http://192.168.1.219:8048"
                        )
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}