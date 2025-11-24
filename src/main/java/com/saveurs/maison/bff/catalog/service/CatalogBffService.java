package com.saveurs.maison.bff.catalog.service;

import com.saveurs.maison.bff.catalog.dto.ApiResponse;
import com.saveurs.maison.bff.catalog.dto.CheeseDto;
import com.saveurs.maison.bff.catalog.dto.WineDto;
import com.saveurs.maison.bff.common.exception.BffNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogBffService {

    private final RestClient client;

    // ------------------------
    // WINES
    // ------------------------

    public List<WineDto> getAllWines() {
        ApiResponse<WineDto[]> response = client.get()
                .uri("/wines")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return Arrays.asList(response.getData());
    }

    public WineDto getWineById(String id) {
        try {
            ApiResponse<WineDto> response = client.get()
                    .uri("/wines/{id}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return response.getData();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new BffNotFoundException("Wine not found");
        }
    }

    public WineDto createWine(WineDto dto) {
        ApiResponse<WineDto> response = client.post()
                .uri("/wines")
                .body(dto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return response.getData();
    }

    public WineDto updateWine(String id, WineDto dto) {
        ApiResponse<WineDto> response = client.put()
                .uri("/wines/{id}", id)
                .body(dto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return response.getData();
    }

    public void deleteWine(String id) {
        try {
            client.delete()
                    .uri("/wines/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new BffNotFoundException("Wine not found");
        }
    }

    // ------------------------
    // CHEESES
    // ------------------------

    public List<CheeseDto> getAllCheeses() {
        ApiResponse<CheeseDto[]> response = client.get()
                .uri("/cheeses")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return Arrays.asList(response.getData());
    }

    public CheeseDto getCheeseById(String id) {
        try {
            ApiResponse<CheeseDto> response = client.get()
                    .uri("/cheeses/{id}", id)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            return response.getData();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new BffNotFoundException("Cheese not found");
        }
    }

    public CheeseDto createCheese(CheeseDto dto) {
        ApiResponse<CheeseDto> response = client.post()
                .uri("/cheeses")
                .body(dto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return response.getData();
    }

    public CheeseDto updateCheese(String id, CheeseDto dto) {
        ApiResponse<CheeseDto> response = client.put()
                .uri("/cheeses/{id}", id)
                .body(dto)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
        return response.getData();
    }

    public void deleteCheese(String id) {
        try {
            client.delete()
                    .uri("/cheeses/{id}", id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException.NotFound ex) {
            throw new BffNotFoundException("Cheese not found");
        }
    }
}
