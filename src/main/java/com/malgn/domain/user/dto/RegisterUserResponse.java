package com.malgn.domain.user.dto;

import com.malgn.domain.user.entity.User;
import com.malgn.domain.user.entity.UserRole;
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
