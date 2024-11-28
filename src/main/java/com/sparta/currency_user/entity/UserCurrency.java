package com.sparta.currency_user.entity;

import com.sparta.currency_user.status.UserCurrencyStatus;
import jakarta.persistence.*;
import lombok.Getter;

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
    private String amountAfterExchange;

    @Enumerated(EnumType.STRING)
    private UserCurrencyStatus userCurrencyStatus;

    public UserCurrency(User user, Currency currency, Long cost, String stringResult) {
        this.user = user;
        this.currency = currency;
        this.amountIntKrw = cost;
        this.amountAfterExchange = stringResult;
        this.userCurrencyStatus = UserCurrencyStatus.NORMAL;
    }

    public void updateUserCurrencyStatus () {
        this.userCurrencyStatus = UserCurrencyStatus.CANCELLED;
    }

    public UserCurrency() {

    }
}
