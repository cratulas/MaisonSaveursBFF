package com.saveurs.maison.bff.users.client;

import com.saveurs.maison.bff.security.UserIdentity;
import com.saveurs.maison.bff.users.dto.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class UserServiceClient {

    private final WebClient userServiceWebClient;

    public UserServiceClient(WebClient userServiceWebClient) {
        this.userServiceWebClient = userServiceWebClient;
    }

    // ============================
    // PROFILE
    // ============================

    public UserProfileDto getOrCreateProfile(UserIdentity identity) {

        return userServiceWebClient.get()
                .uri("/api/users/me")
                .headers(h -> {
                    h.set("X-User-Id", identity.getUserId());
                    h.set("X-User-Email", identity.getEmail());
                    h.set("X-User-Name", nullSafe(identity.getFirstName()));
                    h.set("X-User-Lastname", nullSafe(identity.getLastName()));
                    h.set("X-User-Role", "CUSTOMER");   // por ahora, MSUsuario asigna el rol real
                })
                .retrieve()
                .bodyToMono(UserProfileDto.class)
                .block();
    }

    public UserProfileDto updateProfile(String userId, UpdateUserProfileRequest request) {

        return userServiceWebClient.put()
                .uri("/api/users/me")
                .header("X-User-Id", userId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UserProfileDto.class)
                .block();
    }

    // ============================
    // PREFERENCES
    // ============================

    public UserPreferencesDto getPreferences(String userId) {

        return userServiceWebClient.get()
                .uri("/api/users/me/preferences")
                .header("X-User-Id", userId)
                .retrieve()
                .bodyToMono(UserPreferencesDto.class)
                .block();
    }

    public UserPreferencesDto updatePreferences(String userId,
                                                UpdateUserPreferencesRequest request) {

        return userServiceWebClient.put()
                .uri("/api/users/me/preferences")
                .header("X-User-Id", userId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UserPreferencesDto.class)
                .block();
    }

    // ============================
    // WISHLIST
    // ============================

    public List<WishlistItemDto> getWishlist(String userId) {

        return userServiceWebClient.get()
                .uri("/api/users/me/wishlist")
                .header("X-User-Id", userId)
                .retrieve()
                .bodyToFlux(WishlistItemDto.class)
                .collectList()
                .block();
    }

    public WishlistItemDto addToWishlist(String userId, WishlistItemRequest request) {

        return userServiceWebClient.post()
                .uri("/api/users/me/wishlist")
                .header("X-User-Id", userId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(WishlistItemDto.class)
                .block();
    }

    public void removeFromWishlist(String userId, String wishlistItemId) {

        userServiceWebClient.delete()
                .uri("/api/users/me/wishlist/{id}", wishlistItemId)
                .header("X-User-Id", userId)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    // ============================
    // HELPERS
    // ============================

    private String nullSafe(String value) {
        return value == null ? "" : value;
    }
}
