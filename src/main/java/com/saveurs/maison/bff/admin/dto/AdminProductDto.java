package com.saveurs.maison.bff.admin.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdminProductDto {
    private String id;
    private String type; // "WINE" o "CHEESE"
    private String nameEn;
    private String nameFr;
    private String imageUrl;
    private String origin;
    private BigDecimal price;
    private boolean available;
}
