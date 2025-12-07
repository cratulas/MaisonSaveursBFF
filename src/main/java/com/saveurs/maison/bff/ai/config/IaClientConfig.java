package com.saveurs.maison.bff.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class IaClientConfig {

    @Bean
    public WebClient iaWebClient(@Value("${ia.base-url}") String iaBaseUrl,
                                 WebClient.Builder builder) {
        return builder
                .baseUrl(iaBaseUrl)
                .build();
    }
}
