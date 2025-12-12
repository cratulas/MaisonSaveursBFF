package com.saveurs.maison.bff.admin.client;

import com.saveurs.maison.bff.admin.dto.AdminUserProfileDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class AdminUsersClient {

    private final WebClient adminWebClient;

    public AdminUsersClient(@Qualifier("adminWebClient") WebClient adminWebClient) {
        this.adminWebClient = adminWebClient;
    }

    public List<AdminUserProfileDto> getAllUsers() {
        return adminWebClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(AdminUserProfileDto.class)
                .collectList()
                .block();
    }

    public AdminUserProfileDto updateUserRole(String userId, String newRole) {
        return adminWebClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/{id}/role")
                        .queryParam("role", newRole)
                        .build(userId))
                .retrieve()
                .bodyToMono(AdminUserProfileDto.class)
                .block();
    }
}
