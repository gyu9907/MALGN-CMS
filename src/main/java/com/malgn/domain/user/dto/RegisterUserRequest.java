package com.malgn.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank
    @Size(max = 50, min = 2, message = "아이디는 2~50자 사이여야 합니다.")
    private String username;

    @NotBlank
    @Size(max = 30, min = 8, message = "비밀번호는 8~30자 사이여야 합니다.")
    private String password;
}
