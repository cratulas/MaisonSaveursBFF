package com.saveurs.maison.bff.admin.client;

import com.saveurs.maison.bff.admin.dto.HomeCmsConfigDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AdminCmsClient {

    private final WebClient adminWebClient;

    public AdminCmsClient(@Qualifier("adminWebClient") WebClient adminWebClient) {
        this.adminWebClient = adminWebClient;
    }

    public HomeCmsConfigDto getHomeConfig() {
        return adminWebClient.get()
                .uri("/cms/home")
                .retrieve()
                .bodyToMono(HomeCmsConfigDto.class)
                .block();
    }

    public HomeCmsConfigDto updateHomeConfig(HomeCmsConfigDto body) {
        return adminWebClient.put()
                .uri("/cms/home")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(HomeCmsConfigDto.class)
                .block();
    }
}
