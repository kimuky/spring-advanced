package com.sparta.currency_user.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public class UserCurrencyRequestDto {

    private final Long userId;
    private final Long cost;
    private final String currencyName;

    public UserCurrencyRequestDto(Long userId, Long cost, String currencyName) {
        this.userId = userId;
        this.cost = cost;
        this.currencyName = currencyName;
    }
}
