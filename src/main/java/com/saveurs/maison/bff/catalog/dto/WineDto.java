package com.saveurs.maison.bff.catalog.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class WineDto {

    private String id;

    private String nameEn;
    private String nameFr;

    private String descriptionEn;
    private String descriptionFr;

    private String imageUrl;
    private String origin;
    private String grape;

    private WineType type;
    private List<WineFlavor> flavors;

    private int sweetnessLevel;
    private int body;

    private BigDecimal price;
    private boolean available;

    private Double alcoholPercentage;
    private String servingTemperature;
}
