package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findCurrencyByCurrencyName(String currencyName);

    List<Currency> findCurrencyByExchangeRateLessThanEqual(BigDecimal exchangeRate);
}
