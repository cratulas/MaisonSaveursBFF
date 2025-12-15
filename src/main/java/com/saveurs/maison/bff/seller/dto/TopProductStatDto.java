package com.saveurs.maison.bff.seller.dto;

import java.time.Instant;

public class TopProductStatDto {

    private String productType;
    private String productId;
    private long recommendationCount;
    private Instant lastRecommendedAt;

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public long getRecommendationCount() { return recommendationCount; }
    public void setRecommendationCount(long recommendationCount) {
        this.recommendationCount = recommendationCount;
    }

    public Instant getLastRecommendedAt() { return lastRecommendedAt; }
    public void setLastRecommendedAt(Instant lastRecommendedAt) {
        this.lastRecommendedAt = lastRecommendedAt;
    }
}
