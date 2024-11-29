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

    // 유저 아이디 를 그룹바이 하여 유저 아이디 단위로 묶이며 각 유저의 레코드 수와 환전 요청한 합을 조회
    @Query(value = "SELECT new com.sparta.currency_user.dto." +
            "UserCurrencyCountTotalResponseDto(uc.user.id,COUNT(*), SUM(uc.amountIntKrw)) " +
            "FROM user_currency uc GROUP BY uc.user"
    )
    List<UserCurrencyCountTotalResponseDto> findCustomAll();


    // 특정 유저 아이디를 조회하고 해당 유저의 환전 요청 레코드 수와 한전 요청 금액을 조회
    @Query(value = "SELECT new com.sparta.currency_user.dto." +
            "UserCurrencyCountTotalResponseDto(uc.user.id,COUNT(*), SUM(uc.amountIntKrw)) " +
            "FROM user_currency uc WHERE uc.user = :user_id"
    )
    UserCurrencyCountTotalResponseDto findCustomByUser(@Param(value = "user_id") User user_id);
}
