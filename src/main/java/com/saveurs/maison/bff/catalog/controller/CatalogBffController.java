package com.saveurs.maison.bff.catalog.controller;

import com.saveurs.maison.bff.catalog.dto.ApiResponse;
import com.saveurs.maison.bff.catalog.dto.CheeseDto;
import com.saveurs.maison.bff.catalog.dto.WineDto;
import com.saveurs.maison.bff.catalog.service.CatalogBffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/bff/catalog")
@RequiredArgsConstructor
public class CatalogBffController {

    private final CatalogBffService service;

    // --------------------
    // WINES
    // --------------------

    @GetMapping("/wines")
    public ApiResponse<List<WineDto>> getWines() {
        return ApiResponse.<List<WineDto>>builder()
                .data(service.getAllWines())
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @GetMapping("/wines/{id}")
    public ApiResponse<WineDto> getWine(@PathVariable String id) {
        return ApiResponse.<WineDto>builder()
                .data(service.getWineById(id))
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @PostMapping("/wines")
    public ApiResponse<WineDto> createWine(@RequestBody WineDto dto) {
        return ApiResponse.<WineDto>builder()
                .data(service.createWine(dto))
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @PutMapping("/wines/{id}")
    public ApiResponse<WineDto> updateWine(@PathVariable String id, @RequestBody WineDto dto) {
        return ApiResponse.<WineDto>builder()
                .data(service.updateWine(id, dto))
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @DeleteMapping("/wines/{id}")
    public ApiResponse<Void> deleteWine(@PathVariable String id) {
        service.deleteWine(id);
        return ApiResponse.<Void>builder()
                .data(null)
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }


    // --------------------
    // CHEESES
    // --------------------

    @GetMapping("/cheeses")
    public ApiResponse<List<CheeseDto>> getCheeses() {
        return ApiResponse.<List<CheeseDto>>builder()
                .data(service.getAllCheeses())
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @GetMapping("/cheeses/{id}")
    public ApiResponse<CheeseDto> getCheese(@PathVariable String id) {
        return ApiResponse.<CheeseDto>builder()
                .data(service.getCheeseById(id))
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @PostMapping("/cheeses")
    public ApiResponse<CheeseDto> createCheese(@RequestBody CheeseDto dto) {
        return ApiResponse.<CheeseDto>builder()
                .data(service.createCheese(dto))
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @PutMapping("/cheeses/{id}")
    public ApiResponse<CheeseDto> updateCheese(@PathVariable String id, @RequestBody CheeseDto dto) {
        return ApiResponse.<CheeseDto>builder()
                .data(service.updateCheese(id, dto))
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }

    @DeleteMapping("/cheeses/{id}")
    public ApiResponse<Void> deleteCheese(@PathVariable String id) {
        service.deleteCheese(id);
        return ApiResponse.<Void>builder()
                .data(null)
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();
    }
}
