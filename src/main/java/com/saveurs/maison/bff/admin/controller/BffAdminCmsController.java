package com.saveurs.maison.bff.admin.controller;

import com.saveurs.maison.bff.admin.client.AdminCmsClient;
import com.saveurs.maison.bff.admin.dto.HomeCmsConfigDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/admin/cms")
public class BffAdminCmsController {

    private final AdminCmsClient adminCmsClient;

    public BffAdminCmsController(AdminCmsClient adminCmsClient) {
        this.adminCmsClient = adminCmsClient;
    }

    @GetMapping("/home")
    public ResponseEntity<HomeCmsConfigDto> getHomeConfig() {
        return ResponseEntity.ok(adminCmsClient.getHomeConfig());
    }

    @PutMapping("/home")
    public ResponseEntity<HomeCmsConfigDto> updateHomeConfig(
            @RequestBody HomeCmsConfigDto body
    ) {
        return ResponseEntity.ok(adminCmsClient.updateHomeConfig(body));
    }
}
