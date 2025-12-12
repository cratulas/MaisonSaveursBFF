package com.saveurs.maison.bff.admin.dto;

import lombok.Data;

@Data
public class AdminUserProfileDto {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String language;
}
