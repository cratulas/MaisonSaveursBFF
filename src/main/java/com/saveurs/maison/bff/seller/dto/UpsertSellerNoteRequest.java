package com.saveurs.maison.bff.seller.dto;

import lombok.Data;

@Data
public class UpsertSellerNoteRequest {
    private String productId;
    private String productType;
    private String note;
}
