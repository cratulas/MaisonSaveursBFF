package com.saveurs.maison.bff.users.controller;

import com.saveurs.maison.bff.security.UserIdentity;
import com.saveurs.maison.bff.security.UserIdentityExtractor;
import com.saveurs.maison.bff.users.client.UserServiceClient;
import com.saveurs.maison.bff.users.dto.UpdateUserPreferencesRequest;
import com.saveurs.maison.bff.users.dto.UpdateUserProfileRequest;
import com.saveurs.maison.bff.users.dto.UserPreferencesDto;
import com.saveurs.maison.bff.users.dto.UserProfileDto;
import com.saveurs.maison.bff.users.dto.WishlistItemDto;
import com.saveurs.maison.bff.users.dto.WishlistItemRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bff/users")
public class UserBffController {

    private final UserServiceClient userServiceClient;
    private final UserIdentityExtractor identityExtractor;

    public UserBffController(UserServiceClient userServiceClient,
                             UserIdentityExtractor identityExtractor) {
        this.userServiceClient = userServiceClient;
        this.identityExtractor = identityExtractor;
    }

    // ============================================
    // GET /api/bff/users/me
    // ============================================
    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getMyProfile(@AuthenticationPrincipal Jwt jwt) {

        UserIdentity identity = identityExtractor.fromJwt(jwt);

        UserProfileDto profile = userServiceClient.getOrCreateProfile(identity);

        return ResponseEntity.ok(profile);
    }

    // ============================================
    // PUT /api/bff/users/me
    // ============================================
    @PutMapping("/me")
    public ResponseEntity<UserProfileDto> updateMyProfile(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody UpdateUserProfileRequest request) {

        UserIdentity identity = identityExtractor.fromJwt(jwt);

        UserProfileDto profile =
                userServiceClient.updateProfile(identity.getUserId(), request);

        return ResponseEntity.ok(profile);
    }

    // ============================================
    // GET /api/bff/users/me/preferences
    // ============================================
    @GetMapping("/me/preferences")
    public ResponseEntity<UserPreferencesDto> getMyPreferences(
            @AuthenticationPrincipal Jwt jwt) {

        UserIdentity identity = identityExtractor.fromJwt(jwt);

        UserPreferencesDto prefs =
                userServiceClient.getPreferences(identity.getUserId());

        return ResponseEntity.ok(prefs);
    }

    // ============================================
    // PUT /api/bff/users/me/preferences
    // ============================================
    @PutMapping("/me/preferences")
    public ResponseEntity<UserPreferencesDto> updateMyPreferences(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody UpdateUserPreferencesRequest request) {

        UserIdentity identity = identityExtractor.fromJwt(jwt);

        UserPreferencesDto prefs =
                userServiceClient.updatePreferences(identity.getUserId(), request);

        return ResponseEntity.ok(prefs);
    }

    // ============================================
    // GET /api/bff/users/me/wishlist
    // ============================================
    @GetMapping("/me/wishlist")
    public ResponseEntity<List<WishlistItemDto>> getMyWishlist(
            @AuthenticationPrincipal Jwt jwt) {

        UserIdentity identity = identityExtractor.fromJwt(jwt);

        List<WishlistItemDto> wishlist =
                userServiceClient.getWishlist(identity.getUserId());

        return ResponseEntity.ok(wishlist);
    }

    // ============================================
    // POST /api/bff/users/me/wishlist
    // ============================================
    @PostMapping("/me/wishlist")
    public ResponseEntity<WishlistItemDto> addToWishlist(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody WishlistItemRequest request) {

        UserIdentity identity = identityExtractor.fromJwt(jwt);

        WishlistItemDto item =
                userServiceClient.addToWishlist(identity.getUserId(), request);

        return ResponseEntity.ok(item);
    }

    // ============================================
    // DELETE /api/bff/users/me/wishlist/{id}
    // ============================================
    @DeleteMapping("/me/wishlist/{id}")
    public ResponseEntity<Void> removeFromWishlist(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable String id) {

        UserIdentity identity = identityExtractor.fromJwt(jwt);

        userServiceClient.removeFromWishlist(identity.getUserId(), id);

        return ResponseEntity.noContent().build();
    }
}
