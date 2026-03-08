package com.malgn.domain.user.service;

import com.malgn.domain.user.dto.RegisterUserRequest;
import com.malgn.domain.user.dto.RegisterUserResponse;
import com.malgn.domain.user.entity.User;
import com.malgn.domain.user.entity.UserRole;
import com.malgn.domain.user.repository.UserRepository;
import com.malgn.global.exception.BusinessException;
import com.malgn.global.exception.ErrorCode;
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
            throw new BusinessException(ErrorCode.DUPLICATE_USERNAME);
        }

        User user = User.createUser(request.getUsername(), passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return RegisterUserResponse.from(user);
    }

    // 관리자 계정(admin)이 없는 경우 생성
    @Transactional
    public void createAdminIfNotExists() {
        if(userRepository.existsByUsername("admin")) {
            return;
        }

        User user = User.createUser("admin", passwordEncoder.encode("admin"));

        user.changeRole(UserRole.ROLE_ADMIN);

        userRepository.save(user);
    }

    // 테스트를 위한 회원 생성
    public void createTestUsers() {

        createUserIfNotExists("dev_kim", "qwer1234");
        createUserIfNotExists("dev_kim", "backend_kyubeen");

    }

    public void createUserIfNotExists(String username, String password) {

        if(userRepository.existsByUsername(username)) {
            return;
        }

        User user = User.createUser(username, passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
