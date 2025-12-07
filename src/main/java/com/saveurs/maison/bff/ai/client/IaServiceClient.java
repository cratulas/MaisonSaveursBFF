package com.saveurs.maison.bff.ai.client;

import com.saveurs.maison.bff.ai.dto.PairingChatRequest;
import com.saveurs.maison.bff.ai.dto.PairingChatResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
}
