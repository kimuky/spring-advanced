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

    // 환전 요청
    @Transactional
    public void requestExchange(UserCurrencyRequestDto requestDto) {
        User findUser = userRepository.findUser(requestDto.getUserId());

        Currency findCurrency = currencyRepository.findCurrencyByCurrencyName(requestDto.getCurrencyName())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없음"));

        // 비율을 받고, 사용자가 제시한 금액을 통해 계산
        BigDecimal exchangeRate = findCurrency.getExchangeRate();
        BigDecimal costBigDecimal = new BigDecimal(requestDto.getCost());
        BigDecimal divide = costBigDecimal.divide(exchangeRate, 2, RoundingMode.HALF_EVEN);

        UserCurrency userCurrency = new UserCurrency(findUser, findCurrency, requestDto.getCost(), divide);

        userCurrencyRepository.save(userCurrency);
    }

    // 유저 id 를 이용해 유저가 신청한 모든 환전 요청 조회
    @Transactional
    public List<UserCurrencyAllResponseDto> findUserCurrencyRequest(Long userId) {
        User findUser = userRepository.findUser(userId);

        List<UserCurrency> userCurrencyList = userCurrencyRepository.findUserCurrencyByUser(findUser);

        return userCurrencyList.stream().map(UserCurrencyAllResponseDto::new).toList();
    }

    // 모든 유저를 id 단위로 그룹화하여 요청레코드와 총합을 조회
    public List<UserCurrencyCountTotalResponseDto> findUserCurrencyRequestAll() {

        return userCurrencyRepository.findCustomAll();
    }

    // 유저 id를 통해 해당 유저의 요청레코드와 총합을 조회
    @Transactional
    public UserCurrencyCountTotalResponseDto findUserCurrencyRequestByUser(Long userId) {
        User findUser = userRepository.findUser(userId);

        return userCurrencyRepository.findCustomByUser(findUser);
    }

    // 환전 요청 취소
    @Transactional
    public void cancelledRequest(Long userCurrencyId) {
        UserCurrency userCurrency = userCurrencyRepository.findById(userCurrencyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없음"));

        userCurrency.updateUserCurrencyStatus();
    }



}
