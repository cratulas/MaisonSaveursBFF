package com.saveurs.maison.bff.admin.controller;

import com.saveurs.maison.bff.admin.client.AdminUsersClient;
import com.saveurs.maison.bff.admin.dto.AdminUserProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bff/admin/users")
public class BffAdminUsersController {

    private final AdminUsersClient usersClient;

    public BffAdminUsersController(AdminUsersClient usersClient) {
        this.usersClient = usersClient;
    }

    @GetMapping
    public ResponseEntity<List<AdminUserProfileDto>> getAllUsers() {
        return ResponseEntity.ok(usersClient.getAllUsers());
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<AdminUserProfileDto> updateUserRole(
            @PathVariable("id") String userId,
            @RequestParam("role") String newRole
    ) {
        return ResponseEntity.ok(usersClient.updateUserRole(userId, newRole));
    }
}
