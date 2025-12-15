package com.saveurs.maison.bff.seller.controller;

import com.saveurs.maison.bff.security.UserIdentity;
import com.saveurs.maison.bff.security.UserIdentityExtractor;
import com.saveurs.maison.bff.seller.client.SellerAdminClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import com.saveurs.maison.bff.admin.dto.AdminCheeseDto;
import com.saveurs.maison.bff.admin.dto.AdminProductDto;
import com.saveurs.maison.bff.seller.dto.SellerNoteDto;
import com.saveurs.maison.bff.seller.dto.TopProductStatDto;
import com.saveurs.maison.bff.seller.dto.UpsertSellerNoteRequest;


import java.util.List;

@RestController
@RequestMapping("/api/seller")
public class BffSellerController {

    private final SellerAdminClient client;
    private final UserIdentityExtractor extractor;

    public BffSellerController(
            SellerAdminClient client,
            UserIdentityExtractor extractor
    ) {
        this.client = client;
        this.extractor = extractor;
    }

    // PRODUCTS
    @GetMapping("/products")
    public ResponseEntity<List<AdminProductDto>> products() {
        return ResponseEntity.ok(client.getProducts());
    }

    @GetMapping("/products/cheeses/{id}")
    public ResponseEntity<AdminCheeseDto> cheese(@PathVariable String id) {
        return ResponseEntity.ok(client.getCheese(id));
    }

    // NOTES
    @PutMapping("/notes")
    public ResponseEntity<SellerNoteDto> upsertNote(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody UpsertSellerNoteRequest body
    ) {
        UserIdentity user = extractor.fromJwt(jwt);
        return ResponseEntity.ok(client.upsertNote(user.getUserId(), body));
    }

    @GetMapping("/notes")
    public ResponseEntity<SellerNoteDto> getNote(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam String productType,
            @RequestParam String productId
    ) {
        UserIdentity user = extractor.fromJwt(jwt);
        return ResponseEntity.ok(
                client.getNote(user.getUserId(), productType, productId)
        );
    }

    @DeleteMapping("/notes")
    public ResponseEntity<Void> deleteNote(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam String productType,
            @RequestParam String productId
    ) {
        UserIdentity user = extractor.fromJwt(jwt);
        client.deleteNote(user.getUserId(), productType, productId);
        return ResponseEntity.noContent().build();
    }

    // ANALYTICS
    @GetMapping("/analytics/top-recommended")
    public ResponseEntity<List<TopProductStatDto>> analytics(
            @RequestParam(defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(client.topRecommended(limit));
    }
}
