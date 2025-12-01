package com.saveurs.maison.bff.users.dto;

import lombok.Data;

@Data
public class UserPreferencesDto {
    private String userId;
    private String favoriteWineType;
    private String cheeseIntensity;
    private String favoriteRegion;
}
