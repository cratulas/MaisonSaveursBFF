package com.saveurs.maison.bff.ai.dto;

import java.time.Instant;
import java.util.List;

public class PairingHistoryItemResponse {

    private String id;
    private String userId;
    private String locale;
    private String source;
    private String message;
    private List<String> selectedWineIds;
    private List<String> selectedCheeseIds;
    private String answer;
    private List<String> recommendedWineIds;
    private List<String> recommendedCheeseIds;
    private Instant createdAt;

    public PairingHistoryItemResponse() {}

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getLocale() { return locale; }
    public void setLocale(String locale) { this.locale = locale; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<String> getSelectedWineIds() { return selectedWineIds; }
    public void setSelectedWineIds(List<String> selectedWineIds) { this.selectedWineIds = selectedWineIds; }

    public List<String> getSelectedCheeseIds() { return selectedCheeseIds; }
    public void setSelectedCheeseIds(List<String> selectedCheeseIds) { this.selectedCheeseIds = selectedCheeseIds; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public List<String> getRecommendedWineIds() { return recommendedWineIds; }
    public void setRecommendedWineIds(List<String> recommendedWineIds) { this.recommendedWineIds = recommendedWineIds; }

    public List<String> getRecommendedCheeseIds() { return recommendedCheeseIds; }
    public void setRecommendedCheeseIds(List<String> recommendedCheeseIds) { this.recommendedCheeseIds = recommendedCheeseIds; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
