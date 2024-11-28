package com.sparta.currency_user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserCurrencyRequestDto {

    @NotNull(message = "유저 아이디 입력해주세요")
    @Positive
    private final Long userId;

    @NotNull(message = "돈을 입력해주세요")
    @Min(value = 1000, message = "1000원 이상부터 환전이 가능")
    private final Long cost;

    @Pattern(regexp = "^[A-Z]+$", message = "대문자만 입력해주세요.")
    private final String currencyName;

    public UserCurrencyRequestDto(Long userId, Long cost, String currencyName) {
        this.userId = userId;
        this.cost = cost;
        this.currencyName = currencyName;
    }
}
