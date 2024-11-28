package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @Size(max = 20, message = "20글자까지만 입력이 가능합니다.")
    @NotBlank(message = "이름 입력해주세요")
    private String name;

    @NotBlank(message = "이메일 입력해주세요")
    @Email(message = "이메일 양식 지켜주세요")
    private String email;

    public User toEntity() {
        return new User(
                this.name,
                this.email
        );
    }
}
