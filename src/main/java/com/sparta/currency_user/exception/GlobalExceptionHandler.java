package com.sparta.currency_user.exception;

import com.sparta.currency_user.dto.StatusCodeMessageResponseDto;
import com.sparta.currency_user.dto.StatusMessageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    // @Valid 에 따른 예외 처리 출력
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StatusMessageResponseDto>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        List<StatusMessageResponseDto> responseDtoList
                = errors.stream().map(StatusMessageResponseDto::new).toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDtoList);
    }

    // ResponseStatusException 에 따른 예외처리
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StatusCodeMessageResponseDto> handleIllegalArgumentExceptions(ResponseStatusException ex) {
        StatusCodeMessageResponseDto responseDto = new StatusCodeMessageResponseDto(ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

}
