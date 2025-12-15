package com.saveurs.maison.bff.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SellerAdminClientConfig {

    @Bean
    public WebClient sellerAdminWebClient(
            WebClient.Builder builder,
            @Value("${admin.root-url}") String adminRootUrl
    ) {
        return builder
                .baseUrl(adminRootUrl + "/api")
                .build();
    }
}
