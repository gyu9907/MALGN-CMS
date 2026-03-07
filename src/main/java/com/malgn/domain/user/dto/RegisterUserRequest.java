package com.malgn.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
