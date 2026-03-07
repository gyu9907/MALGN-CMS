package com.malgn.domain.user.controller;

import com.malgn.domain.user.dto.RegisterUserRequest;
import com.malgn.domain.user.dto.RegisterUserResponse;
import com.malgn.domain.user.service.UserService;
import com.malgn.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {

        RegisterUserResponse userResponse = userService.saveUser(registerUserRequest);

        return ResponseEntity.ok().body(ApiResponse.success("회원가입이 완료되었습니다.", userResponse));
    }
}
