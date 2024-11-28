package com.sparta.currency_user.dto;

import lombok.Getter;

@Getter
public class UserCurrencyCountTotalResponseDto {

    private final Long userId;
    private final Long count;

    private final Long totalAmountInKrw;

    public UserCurrencyCountTotalResponseDto(Long userId, Long count, Long amount) {
        this.userId = userId;
        this.count = count;
        this.totalAmountInKrw = amount;
    }

}
