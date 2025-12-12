package com.saveurs.maison.bff.admin.client;

import com.saveurs.maison.bff.admin.dto.AdminCheeseDto;
import com.saveurs.maison.bff.admin.dto.AdminProductDto;
import com.saveurs.maison.bff.admin.dto.AdminWineDto;
import com.saveurs.maison.bff.admin.dto.CreateUpdateCheeseRequest;
import com.saveurs.maison.bff.admin.dto.CreateUpdateWineRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class AdminProductsClient {

    private final WebClient adminWebClient;

    public AdminProductsClient(@Qualifier("adminWebClient") WebClient adminWebClient) {
        this.adminWebClient = adminWebClient;
    }

    public List<AdminProductDto> getAllProducts() {
        return adminWebClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(AdminProductDto.class)
                .collectList()
                .block();
    }

    public AdminWineDto getWine(String id) {
        return adminWebClient.get()
                .uri("/products/wines/{id}", id)
                .retrieve()
                .bodyToMono(AdminWineDto.class)
                .block();
    }

    public AdminCheeseDto getCheese(String id) {
        return adminWebClient.get()
                .uri("/products/cheeses/{id}", id)
                .retrieve()
                .bodyToMono(AdminCheeseDto.class)
                .block();
    }

    public AdminWineDto createWine(CreateUpdateWineRequest request) {
        return adminWebClient.post()
                .uri("/products/wines")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AdminWineDto.class)
                .block();
    }

    public AdminCheeseDto createCheese(CreateUpdateCheeseRequest request) {
        return adminWebClient.post()
                .uri("/products/cheeses")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AdminCheeseDto.class)
                .block();
    }

    public AdminWineDto updateWine(String id, CreateUpdateWineRequest request) {
        return adminWebClient.put()
                .uri("/products/wines/{id}", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AdminWineDto.class)
                .block();
    }

    public AdminCheeseDto updateCheese(String id, CreateUpdateCheeseRequest request) {
        return adminWebClient.put()
                .uri("/products/cheeses/{id}", id)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AdminCheeseDto.class)
                .block();
    }

    public void deleteWine(String id) {
        adminWebClient.delete()
                .uri("/products/wines/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public void deleteCheese(String id) {
        adminWebClient.delete()
                .uri("/products/cheeses/{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
