package com.sparta.currency_user.repository;

import com.sparta.currency_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 호출되는 곳이 많아서 default 로 설정
    default User findUser (Long id ) {
        return findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없음"));
    }
}
