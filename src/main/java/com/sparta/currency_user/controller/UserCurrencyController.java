package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.StatusAndMessageResponseDto;
import com.sparta.currency_user.dto.UserCurrencyAllResponseDto;
import com.sparta.currency_user.dto.UserCurrencyRequestDto;
import com.sparta.currency_user.service.UserCurrencyService;
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
    public ResponseEntity<StatusAndMessageResponseDto> requestCurrencyExchange (@RequestBody UserCurrencyRequestDto requestDto) {
        userCurrencyService.requestExchange(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new StatusAndMessageResponseDto(HttpStatus.CREATED,"환전 요청 완료"));
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<UserCurrencyAllResponseDto>> findUserCurrencyRequest (@PathVariable Long userId) {
        List<UserCurrencyAllResponseDto> userCurrencyList = userCurrencyService.findUserCurrencyRequest(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userCurrencyList);
    }

    @PatchMapping("/{userCurrencyId}")
    public ResponseEntity<StatusAndMessageResponseDto> cancelledRequest (@PathVariable Long userCurrencyId) {
        userCurrencyService.cancelledRequest(userCurrencyId);

        return ResponseEntity.status(HttpStatus.OK).body(new StatusAndMessageResponseDto(HttpStatus.NO_CONTENT, "취소 요청 완료"));
    }

}
