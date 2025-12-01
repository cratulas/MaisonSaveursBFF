package com.saveurs.maison.bff.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WishlistItemRequest {
    @NotBlank
    private String productId;
}
