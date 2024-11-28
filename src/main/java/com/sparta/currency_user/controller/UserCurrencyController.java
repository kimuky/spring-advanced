package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.StatusCodeMessageResponseDto;
import com.sparta.currency_user.dto.UserCurrencyAllResponseDto;
import com.sparta.currency_user.dto.UserCurrencyRequestDto;
import com.sparta.currency_user.service.UserCurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userCurrency")
@RequiredArgsConstructor
public class UserCurrencyController {

    private final UserCurrencyService userCurrencyService;

    @PostMapping
    public ResponseEntity<StatusCodeMessageResponseDto> requestCurrencyExchange (@Valid @RequestBody UserCurrencyRequestDto requestDto) {
        userCurrencyService.requestExchange(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new StatusCodeMessageResponseDto(HttpStatus.CREATED,"환전 요청 완료"));
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<UserCurrencyAllResponseDto>> findUserCurrencyRequest (@PathVariable Long userId) {
        List<UserCurrencyAllResponseDto> userCurrencyList = userCurrencyService.findUserCurrencyRequest(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userCurrencyList);
    }

    @PatchMapping("/{userCurrencyId}")
    public ResponseEntity<StatusCodeMessageResponseDto> cancelledRequest (@PathVariable Long userCurrencyId) {
        userCurrencyService.cancelledRequest(userCurrencyId);

        return ResponseEntity.status(HttpStatus.OK).body(new StatusCodeMessageResponseDto(HttpStatus.NO_CONTENT, "취소 요청 완료"));
    }

}
