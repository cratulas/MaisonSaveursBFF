package com.saveurs.maison.bff.users.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private String language;
}
