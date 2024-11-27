package com.sparta.currency_user.entity;

import com.sparta.currency_user.status.UserCurrencyStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Entity(name = "user_currency")
public class UserCurrency extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "to_currency_id")
    private Currency currency;

    private Long amountIntKrw;
    private BigDecimal amountAfterExchange;

    @Enumerated(EnumType.STRING)
    private UserCurrencyStatus userCurrencyStatus;

    public UserCurrency() {
    }
}
