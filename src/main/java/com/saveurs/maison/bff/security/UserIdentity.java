package com.saveurs.maison.bff.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserIdentity {

    private String userId;     // "sub" del token B2C
    private String email;      // email del usuario
    private String firstName;  // given_name
    private String lastName;   // family_name
}
