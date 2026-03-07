package com.malgn.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.Authentication;

@Getter
@Builder
public class LoginResponse {
    private String username;
    private String role;

    public static LoginResponse from(Authentication authentication) {
        return LoginResponse.builder()
                .username(authentication.getName())
                .role(authentication.getAuthorities().iterator().next().getAuthority())
                .build();
    }
}
