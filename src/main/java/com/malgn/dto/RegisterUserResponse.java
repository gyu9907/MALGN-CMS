package com.malgn.dto;

import com.malgn.entity.User;
import com.malgn.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class RegisterUserResponse {
    private String username;
    private UserRole role;
    private LocalDateTime createdDate;

    public static RegisterUserResponse from(User user) {
        return RegisterUserResponse.builder()
                .username(user.getUsername())
                .role(user.getRole())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
