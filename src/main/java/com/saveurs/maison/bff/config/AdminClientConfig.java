package com.saveurs.maison.bff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AdminClientConfig {

    @Value("${admin.base-url}")
    private String adminBaseUrl;

    @Bean
    public WebClient adminWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl(adminBaseUrl)
                .build();
    }
}
