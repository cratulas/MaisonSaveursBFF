package com.saveurs.maison.bff.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserIdentityExtractor {

    public UserIdentity fromJwt(Jwt jwt) {

        String userId = jwt.getSubject();
        String email = null;

        if (jwt.hasClaim("email")) {
            email = jwt.getClaimAsString("email");
        } else if (jwt.hasClaim("emails")) {
            List<String> emails = jwt.getClaimAsStringList("emails");
            if (emails != null && !emails.isEmpty()) {
                email = emails.get(0);
            }
        }

        String firstName = jwt.getClaimAsString("given_name");
        String lastName = jwt.getClaimAsString("family_name");

        return UserIdentity.builder()
                .userId(userId)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
