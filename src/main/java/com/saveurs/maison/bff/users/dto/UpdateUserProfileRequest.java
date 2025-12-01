package com.saveurs.maison.bff.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserProfileRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String language;
}
