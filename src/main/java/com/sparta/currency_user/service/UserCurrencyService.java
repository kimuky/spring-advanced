package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.UserCurrencyAllResponseDto;
import com.sparta.currency_user.dto.UserCurrencyCountTotalResponseDto;
import com.sparta.currency_user.dto.UserCurrencyRequestDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.entity.UserCurrency;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.UserCurrencyRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCurrencyService {

    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;
    private final UserCurrencyRepository userCurrencyRepository;
    private final UserService userService;

    @Transactional
    public void requestExchange(UserCurrencyRequestDto requestDto) {
        User findUser = userRepository.findUser(requestDto.getUserId());

        Currency findCurrency = currencyRepository.findCurrencyByCurrencyName(requestDto.getCurrencyName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없음"));

        BigDecimal exchangeRate = findCurrency.getExchangeRate();
        BigDecimal costBigDecimal = new BigDecimal(requestDto.getCost());
        BigDecimal divide = costBigDecimal.divide(exchangeRate, 2, RoundingMode.HALF_EVEN);

        UserCurrency userCurrency = new UserCurrency(findUser, findCurrency, requestDto.getCost(), divide);

        userCurrencyRepository.save(userCurrency);
    }

    @Transactional
    public List<UserCurrencyAllResponseDto> findUserCurrencyRequest(Long userId) {
        User findUser = userRepository.findUser(userId);

        List<UserCurrency> userCurrencyList = userCurrencyRepository.findUserCurrencyByUser(findUser);

        return userCurrencyList.stream().map(UserCurrencyAllResponseDto::new).toList();
    }

    public List<UserCurrencyCountTotalResponseDto> findUserCurrencyRequestAll() {

        return userCurrencyRepository.findCustomAll();
    }

    @Transactional
    public UserCurrencyCountTotalResponseDto findUserCurrencyRequestByUser(Long userId) {
        User findUser = userRepository.findUser(userId);


        return userCurrencyRepository.findCustomByUser(findUser);
    }

    @Transactional
    public void cancelledRequest(Long userCurrencyId) {
        UserCurrency userCurrency = userCurrencyRepository.findById(userCurrencyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없음"));

        userCurrency.updateUserCurrencyStatus();
    }



}
