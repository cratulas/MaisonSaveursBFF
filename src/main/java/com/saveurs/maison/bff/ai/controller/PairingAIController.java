package com.saveurs.maison.bff.ai.controller;

import com.saveurs.maison.bff.ai.client.IaServiceClient;
import com.saveurs.maison.bff.ai.dto.PairingChatRequest;
import com.saveurs.maison.bff.ai.dto.PairingChatResponse;
import com.saveurs.maison.bff.ai.dto.PairingHistoryItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/bff/ai/pairings")
@CrossOrigin(origins = "*") // Restringir luego
public class PairingAIController {

    private final IaServiceClient iaServiceClient;

    public PairingAIController(IaServiceClient iaServiceClient) {
        this.iaServiceClient = iaServiceClient;
    }

    @PostMapping("/chat")
    public ResponseEntity<PairingChatResponse> chat(
            @RequestBody PairingChatRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId
    ) {

        // Si el header viene desde el frontend logeado, lo usamos
        if (userId != null && !userId.isBlank()) {
            request.setUserId(userId);
        }
        // Si no viene → se queda null (usuario anónimo)

        PairingChatResponse response = iaServiceClient.chat(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Historial de recomendaciones por usuario.
     * Lee el userId desde el header X-User-Id.
     */
    @GetMapping("/history")
    public ResponseEntity<List<PairingHistoryItemResponse>> history(
            @RequestHeader(value = "X-User-Id", required = false) String userId
    ) {
        // Si no hay userId, devolvemos lista vacía (front mostrará mensaje tipo "login para ver historial")
        if (userId == null || userId.isBlank()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<PairingHistoryItemResponse> history = iaServiceClient.getHistory(userId);
        return ResponseEntity.ok(history);
    }
}
