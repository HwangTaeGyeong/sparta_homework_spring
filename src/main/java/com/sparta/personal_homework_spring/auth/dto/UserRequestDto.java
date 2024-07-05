package com.sparta.personal_homework_spring.auth.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "사용자 이름은 숫자, 영어 대소문자만 가능합니다.")
    @Size(min = 2, max = 10, message = "사용자 이름은 2자 이상 10자 이하여야합니다.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "사용자 이름은 숫자, 영어 대소문자만 가능합니다.")
    @Size(min = 4, max = 12, message = "사용자 이름은 2자 이상 10자 이하여야합니다.")
    private String password;

    @NotBlank
    @Email(message = "이메일 형식을 확인해주세요.")
    private String email;
}
