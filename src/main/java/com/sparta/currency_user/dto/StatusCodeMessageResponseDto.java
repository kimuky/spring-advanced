package com.sparta.currency_user.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class StatusCodeMessageResponseDto {

    private final int statusCode;
    private final String message;

    public StatusCodeMessageResponseDto(HttpStatus created, String message) {
        this.statusCode = created.value();
        this.message = message;
    }

    public StatusCodeMessageResponseDto(ResponseStatusException error) {
        this.statusCode = error.getStatusCode().value();
        this.message = error.getReason();
    }
}
