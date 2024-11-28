package com.sparta.currency_user.dto;

import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class StatusMessageResponseDto {

    private final String status;
    private final String message;

    public StatusMessageResponseDto(FieldError fieldError) {
        this.status = fieldError.getCode();
        this.message = fieldError.getDefaultMessage();
    }
}
