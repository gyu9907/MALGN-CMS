package com.malgn.domain.user.controller;

import com.malgn.dto.RegisterUserRequest;
import com.malgn.dto.RegisterUserResponse;
import com.malgn.service.UserService;
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
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest) {
        RegisterUserResponse user = userService.saveUser(registerUserRequest);
        return ResponseEntity.ok().body(user);
    }
}
