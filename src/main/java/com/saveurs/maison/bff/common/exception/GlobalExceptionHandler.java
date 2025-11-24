package com.saveurs.maison.bff.common.exception;

import com.saveurs.maison.bff.catalog.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.UUID;

@RestControllerAdvice(basePackages = "com.saveurs.maison.bff")
public class GlobalExceptionHandler {

    @ExceptionHandler(BffNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(BffNotFoundException ex) {

        ApiResponse.ApiError error = ApiResponse.ApiError.builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .details(null)
                .build();

        ApiResponse<Void> body = ApiResponse.<Void>builder()
                .data(null)
                .error(error)
                .timestamp(Instant.now())
                .requestId(UUID.randomUUID().toString())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
