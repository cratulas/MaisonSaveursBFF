package com.saveurs.maison.bff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class UserServiceClientConfig {

    @Bean
    public WebClient userServiceWebClient(
            WebClient.Builder builder,
            @Value("${services.user.base-url}") String userServiceBaseUrl
    ) {
        return builder
                .baseUrl(userServiceBaseUrl)
                .build();
    }
}
