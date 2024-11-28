package com.sparta.currency_user.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StatusAndMessageResponseDto {

    private final String statusCode;
    private final String message;

    public StatusAndMessageResponseDto(HttpStatus created, String message) {
        this.statusCode = created.name();
        this.message = message;
    }
}
