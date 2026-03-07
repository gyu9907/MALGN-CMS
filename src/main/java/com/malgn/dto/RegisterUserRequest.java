package com.malgn.dto;

import com.malgn.entity.UserRole;
import lombok.Getter;

@Getter
public class RegisterUserRequest {
    private String username;
    private String password;
}
