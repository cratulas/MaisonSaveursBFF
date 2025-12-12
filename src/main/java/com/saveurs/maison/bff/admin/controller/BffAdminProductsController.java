package com.saveurs.maison.bff.admin.controller;

import com.saveurs.maison.bff.admin.client.AdminProductsClient;
import com.saveurs.maison.bff.admin.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bff/admin/products")
public class BffAdminProductsController {

    private final AdminProductsClient productsClient;

    public BffAdminProductsController(AdminProductsClient productsClient) {
        this.productsClient = productsClient;
    }

    @GetMapping
    public ResponseEntity<List<AdminProductDto>> getAllProducts() {
        return ResponseEntity.ok(productsClient.getAllProducts());
    }

    @GetMapping("/wines/{id}")
    public ResponseEntity<AdminWineDto> getWine(@PathVariable String id) {
        return ResponseEntity.ok(productsClient.getWine(id));
    }

    @GetMapping("/cheeses/{id}")
    public ResponseEntity<AdminCheeseDto> getCheese(@PathVariable String id) {
        return ResponseEntity.ok(productsClient.getCheese(id));
    }

    @PostMapping("/wines")
    public ResponseEntity<AdminWineDto> createWine(@RequestBody CreateUpdateWineRequest request) {
        return ResponseEntity.ok(productsClient.createWine(request));
    }

    @PostMapping("/cheeses")
    public ResponseEntity<AdminCheeseDto> createCheese(@RequestBody CreateUpdateCheeseRequest request) {
        return ResponseEntity.ok(productsClient.createCheese(request));
    }

    @PutMapping("/wines/{id}")
    public ResponseEntity<AdminWineDto> updateWine(
            @PathVariable String id,
            @RequestBody CreateUpdateWineRequest request
    ) {
        return ResponseEntity.ok(productsClient.updateWine(id, request));
    }

    @PutMapping("/cheeses/{id}")
    public ResponseEntity<AdminCheeseDto> updateCheese(
            @PathVariable String id,
            @RequestBody CreateUpdateCheeseRequest request
    ) {
        return ResponseEntity.ok(productsClient.updateCheese(id, request));
    }

    @DeleteMapping("/wines/{id}")
    public ResponseEntity<Void> deleteWine(@PathVariable String id) {
        productsClient.deleteWine(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cheeses/{id}")
    public ResponseEntity<Void> deleteCheese(@PathVariable String id) {
        productsClient.deleteCheese(id);
        return ResponseEntity.noContent().build();
    }
}
