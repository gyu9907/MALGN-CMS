package com.malgn.service;

import com.malgn.dto.RegisterUserRequest;
import com.malgn.dto.RegisterUserResponse;
import com.malgn.entity.User;
import com.malgn.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterUserResponse saveUser(RegisterUserRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 회원명 입니다.");
        }

        User user = User.createUser(request.getUsername(), passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return RegisterUserResponse.from(user);
    }

}
