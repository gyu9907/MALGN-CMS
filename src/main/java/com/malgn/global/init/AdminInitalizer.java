package com.malgn.global.init;

import com.malgn.domain.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminInitalizer {
    private final UserService userService;

    @PostConstruct
    public void init(){
        userService.createAdminIfNotExists();
    }
}
