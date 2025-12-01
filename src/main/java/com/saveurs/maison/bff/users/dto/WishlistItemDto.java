package com.saveurs.maison.bff.users.dto;

import lombok.Data;

@Data
public class WishlistItemDto {
    private String id;
    private String userId;
    private String productId;
    private long createdAt;
}
