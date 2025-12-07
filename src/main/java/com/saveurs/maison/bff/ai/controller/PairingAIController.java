package com.saveurs.maison.bff.ai.controller;

import com.saveurs.maison.bff.ai.client.IaServiceClient;
import com.saveurs.maison.bff.ai.dto.PairingChatRequest;
import com.saveurs.maison.bff.ai.dto.PairingChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/ai/pairings")
@CrossOrigin(origins = "*") // luego puedes restringir al dominio de Ionic
public class PairingAIController {

    private final IaServiceClient iaServiceClient;

    public PairingAIController(IaServiceClient iaServiceClient) {
        this.iaServiceClient = iaServiceClient;
    }

    @PostMapping("/chat")
    public ResponseEntity<PairingChatResponse> chat(
            @RequestBody PairingChatRequest request,
            @AuthenticationPrincipal Jwt jwt       // Token
    ) {

        // Si la llamada viene con token B2C (usuario logeado), sacamos el userId
        if (jwt != null) {
            String userId = jwt.getClaimAsString("oid");
            if (userId == null || userId.isBlank()) {
                userId = jwt.getSubject(); // sub
            }

            request.setUserId(userId);
        }
        // Si jwt es null (usuario anónimo), dejamos request.getUserId() como está (null)
        PairingChatResponse response = iaServiceClient.chat(request);
        return ResponseEntity.ok(response);
    }
}
