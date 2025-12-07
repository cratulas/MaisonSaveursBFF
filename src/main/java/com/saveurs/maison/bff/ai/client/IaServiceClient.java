package com.saveurs.maison.bff.ai.client;

import com.saveurs.maison.bff.ai.dto.PairingChatRequest;
import com.saveurs.maison.bff.ai.dto.PairingChatResponse;
import com.saveurs.maison.bff.ai.dto.PairingHistoryItemResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class IaServiceClient {

    private final WebClient iaWebClient;

    public IaServiceClient(WebClient iaWebClient) {
        this.iaWebClient = iaWebClient;
    }

    public PairingChatResponse chat(PairingChatRequest request) {
        return iaWebClient.post()
                .uri("/ai/pairings/chat")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PairingChatResponse.class)
                .onErrorResume(ex -> {
                    ex.printStackTrace();
                    PairingChatResponse fallback = new PairingChatResponse();
                    fallback.setAnswer("Sorry, I could not generate a recommendation at this moment.");
                    return Mono.just(fallback);
                })
                .block();
    }

    /**
     * Llama al MS de IA para obtener el historial de un usuario.
     */
    public List<PairingHistoryItemResponse> getHistory(String userId) {
        return iaWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/ai/pairings/history")
                        .queryParam("userId", userId)
                        .build()
                )
                .retrieve()
                .bodyToFlux(PairingHistoryItemResponse.class)
                .collectList()
                .onErrorResume(ex -> {
                    ex.printStackTrace();
                    return Mono.just(Collections.emptyList());
                })
                .block();
    }
}
