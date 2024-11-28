package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.UserCurrencyCountTotalResponseDto;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {
    List<UserCurrency> findUserCurrencyByUser(User findUser);

    @Query(value = "SELECT new com.sparta.currency_user.dto." +
            "UserCurrencyCountTotalResponseDto(uc.user.id,COUNT(*), SUM(uc.amountIntKrw)) " +
            "FROM user_currency uc GROUP BY uc.user"
    )
    List<UserCurrencyCountTotalResponseDto> findCustomAll();


    @Query(value = "SELECT new com.sparta.currency_user.dto." +
            "UserCurrencyCountTotalResponseDto(uc.user.id,COUNT(*), SUM(uc.amountIntKrw)) " +
            "FROM user_currency uc WHERE uc.user = :user_id"
    )
    UserCurrencyCountTotalResponseDto findCustomByUser(@Param(value = "user_id") User user_id);
}
