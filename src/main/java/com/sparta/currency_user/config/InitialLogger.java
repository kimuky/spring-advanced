package com.sparta.currency_user.config;

import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.repository.CurrencyRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialLogger {

    private final CurrencyRepository currencyRepository;

    @PostConstruct
    private void init() {
        List<Currency> currencyList = currencyRepository.findCurrencyByExchangeRateLessThanEqual(new BigDecimal(0));

        for (Currency c : currencyList) {
            log.warn("지금 환율 테이블에 부적절한 레코드가 있습니다. id = {}, name = {}, exchangeRate = {}"
                    , c.getId(), c.getCurrencyName(), c.getExchangeRate());
        }
    }
}
