package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    // 통화이름으로 찾기
    Optional<Currency> findCurrencyByCurrencyName(String currencyName);

    // @PostConstruct 를 통해 환전 테이블을 조회하고 이상한 레코드 조회
    List<Currency> findCurrencyByExchangeRateLessThanEqual(BigDecimal exchangeRate);

}
