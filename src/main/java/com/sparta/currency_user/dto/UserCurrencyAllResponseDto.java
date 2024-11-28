package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.UserCurrency;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserCurrencyAllResponseDto {

    private final Long id;
    private final String userName;
    private final String toCurrencyName;
    private final Long amountInKrw;
    private final String amountAfterExchange;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public UserCurrencyAllResponseDto(UserCurrency userCurrency) {
        this.id = userCurrency.getId();
        this.userName = userCurrency.getUser().getName();
        this.toCurrencyName = userCurrency.getCurrency().getCurrencyName();
        this.amountInKrw = userCurrency.getAmountIntKrw();
        this.amountAfterExchange = userCurrency.getAmountAfterExchange();
        this.createAt = userCurrency.getCreatedAt();
        this.updateAt = userCurrency.getUpdatedAt();
    }
}
