package com.saveurs.maison.bff.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeCmsConfigDto {

    private String heroTitle;
    private String heroSubtitle;
    private String heroBackgroundImage;

    // IDs de productos destacados (vinos/quesos)
    private List<String> featuredProductIds;

    // Mensajes adicionales para la home
    private String promoMessage;
    private String bannerTopText;
}
