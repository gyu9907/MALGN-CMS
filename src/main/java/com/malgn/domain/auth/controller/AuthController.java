package com.malgn.domain.auth.controller;

import com.malgn.global.dto.ApiResponse;
import com.malgn.domain.auth.dto.LoginRequest;
import com.malgn.domain.auth.dto.LoginResponse;
import com.malgn.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final SecurityContextRepository securityContextRepository;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest,
                                               HttpServletRequest request,
                                               HttpServletResponse response) {

        LoginResponse data = authService.login(loginRequest, request, response, securityContextRepository);

        return ResponseEntity.ok().body(ApiResponse.success("로그인에 성공했습니다.", data));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false); // 세션 없으면 null 반환

        if (session != null) {
            session.invalidate(); // 세션 무효화
        }

        SecurityContextHolder.clearContext(); // SecurityContext 초기화

        return ResponseEntity.ok().body(ApiResponse.success("로그아웃 되었습니다."));
    }
}
