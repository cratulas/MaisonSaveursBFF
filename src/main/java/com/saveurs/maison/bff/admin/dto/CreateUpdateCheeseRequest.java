package com.saveurs.maison.bff.admin.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateUpdateCheeseRequest {
    private String nameEn;
    private String nameFr;
    private String descriptionEn;
    private String descriptionFr;
    private String imageUrl;
    private String origin;

    private String type;
    private List<String> flavors;

    private int intensity;

    private BigDecimal price;
    private boolean available;
}
