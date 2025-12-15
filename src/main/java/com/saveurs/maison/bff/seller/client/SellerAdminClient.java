package com.saveurs.maison.bff.seller.client;

import com.saveurs.maison.bff.admin.dto.AdminCheeseDto;
import com.saveurs.maison.bff.admin.dto.AdminProductDto;
import com.saveurs.maison.bff.seller.dto.SellerNoteDto;
import com.saveurs.maison.bff.seller.dto.TopProductStatDto;
import com.saveurs.maison.bff.seller.dto.UpsertSellerNoteRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class SellerAdminClient {

    private final WebClient sellerAdminWebClient;

    public SellerAdminClient(
            @Qualifier("sellerAdminWebClient") WebClient sellerAdminWebClient
    ) {
        this.sellerAdminWebClient = sellerAdminWebClient;
    }

    // ============================
    // PRODUCTS
    // ============================

    public List<AdminProductDto> getProducts() {
        return sellerAdminWebClient.get()
                .uri("/seller/products")
                .retrieve()
                .bodyToFlux(AdminProductDto.class)
                .collectList()
                .block();
    }

    public AdminCheeseDto getCheese(String id) {
        return sellerAdminWebClient.get()
                .uri("/seller/products/cheeses/{id}", id)
                .retrieve()
                .bodyToMono(AdminCheeseDto.class)
                .block();
    }

    // ============================
    // NOTES
    // ============================

    public SellerNoteDto upsertNote(String sellerId, UpsertSellerNoteRequest body) {
        return sellerAdminWebClient.put()
                .uri("/seller/notes")
                .header("X-User-Id", sellerId)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(SellerNoteDto.class)
                .block();
    }

    public SellerNoteDto getNote(String sellerId, String productType, String productId) {
        return sellerAdminWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/seller/notes")
                        .queryParam("productType", productType)
                        .queryParam("productId", productId)
                        .build()
                )
                .header("X-User-Id", sellerId)
                .retrieve()
                .bodyToMono(SellerNoteDto.class)
                .block();
    }

    public void deleteNote(String sellerId, String productType, String productId) {
        sellerAdminWebClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/seller/notes")
                        .queryParam("productType", productType)
                        .queryParam("productId", productId)
                        .build()
                )
                .header("X-User-Id", sellerId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    // ============================
    // ANALYTICS
    // ============================

    public List<TopProductStatDto> topRecommended(int limit) {
        return sellerAdminWebClient.get()
                .uri("/admin/seller/analytics/top-recommended?limit={limit}", limit)
                .retrieve()
                .bodyToFlux(TopProductStatDto.class)
                .collectList()
                .block();
    }
}

