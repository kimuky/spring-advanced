package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.Currency;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CurrencyRequestDto {

    @NotBlank(message = "통화 이름 입력해주세요")
    private String currencyName;

    @NotNull(message = "비율 입력해주세요")
    @Min(0)
    private BigDecimal exchangeRate;

    @NotBlank(message = "심볼을 꼭 넣어주세요")
    private String symbol;

    public Currency toEntity() {
        return new Currency(
                this.currencyName,
                this.exchangeRate,
                this.symbol
        );
    }
}
