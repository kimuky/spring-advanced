package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.StatusCodeMessageResponseDto;
import com.sparta.currency_user.dto.UserCurrencyAllResponseDto;
import com.sparta.currency_user.dto.UserCurrencyCountTotalResponseDto;
import com.sparta.currency_user.dto.UserCurrencyRequestDto;
import com.sparta.currency_user.service.UserCurrencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userCurrencies")
@RequiredArgsConstructor
public class UserCurrencyController {

    private final UserCurrencyService userCurrencyService;

    // 환전 요청
    @PostMapping
    public ResponseEntity<StatusCodeMessageResponseDto> requestCurrencyExchange (@Valid @RequestBody UserCurrencyRequestDto requestDto) {
        String predicateResult = userCurrencyService.requestExchange(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new StatusCodeMessageResponseDto(HttpStatus.CREATED,predicateResult+" 환전 요청 완료"));
    }

    // 유저 id 에 따른 요청 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<UserCurrencyAllResponseDto>> findUserCurrencyRequest (@PathVariable Long userId) {
        List<UserCurrencyAllResponseDto> userCurrencyList = userCurrencyService.findUserCurrencyRequest(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userCurrencyList);
    }

    // 모든 유저 id 기준으로 묶어서 요청한 레코드 갯수와 총합
    @GetMapping("/total")
    public ResponseEntity<List<UserCurrencyCountTotalResponseDto>> findUserCurrencyRequestAll () {
        List<UserCurrencyCountTotalResponseDto> totalResponseDtoList = userCurrencyService.findUserCurrencyRequestAll();

        return ResponseEntity.status(HttpStatus.OK).body(totalResponseDtoList);
    }

    // 특정 id 기준으로 쵸청한 레코드와 총합
    @GetMapping("/total/users/{userId}")
    public ResponseEntity<UserCurrencyCountTotalResponseDto> findUserCurrencyRequestByUser (@PathVariable Long userId) {
        UserCurrencyCountTotalResponseDto countTotalResponseDto = userCurrencyService.findUserCurrencyRequestByUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body(countTotalResponseDto);
    }

    // 환전 요청 취소
    @PatchMapping("/{userCurrencyId}")
    public ResponseEntity<StatusCodeMessageResponseDto> cancelledRequest (@PathVariable Long userCurrencyId) {
        userCurrencyService.cancelledRequest(userCurrencyId);

        return ResponseEntity.status(HttpStatus.OK).body(new StatusCodeMessageResponseDto(HttpStatus.NO_CONTENT, "취소 요청 완료"));
    }

}
