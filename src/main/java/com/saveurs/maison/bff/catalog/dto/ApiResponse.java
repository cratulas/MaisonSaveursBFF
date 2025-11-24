package com.saveurs.maison.bff.catalog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiResponse<T> {

    private T data;
    private ApiError error;
    private Instant timestamp;
    private String requestId;

    @Data
    @Builder
    public static class ApiError {
        private String code;
        private String message;
        private Object details;
    }
}
