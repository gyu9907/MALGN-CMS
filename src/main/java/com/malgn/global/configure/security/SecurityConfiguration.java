package com.malgn.global.configure.security;

import com.malgn.global.security.handler.CustomAccessDeniedHandler;
import com.malgn.global.security.handler.CustomAuthenticationEntryPoint;
import com.malgn.global.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) {

        http.csrf(AbstractHttpConfigurer::disable); // CSRF 비활성화
        http.cors(AbstractHttpConfigurer::disable); // CORS 비활성화

        // 접근 권한 설정
        http.authorizeHttpRequests(
            request -> request
                    .requestMatchers("/user/register").permitAll() // 회원가입
                    .requestMatchers("/auth/login").permitAll()    // 로그인
                    .anyRequest().authenticated());

        // 세션 설정
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 세션 정책: ALWAYS(항상 생성), IF_REQUIRED(필요시), NEVER, STATELESS
//                .sessionFixation().changeSessionId()                      // 로그인 성공 시 세션 ID를 교체하여 세션 고정 공격 방어
//                .maximumSessions(1)                                       // 동일 계정 최대 동시 세션 수
//                .maxSessionsPreventsLogin(false)                          // true: 초과 시 새 로그인 차단 / false: 기존 세션 만료
        );

        // 예외 처리
        http.exceptionHandling(ex -> ex
                .authenticationEntryPoint(authenticationEntryPoint)  // 401
                .accessDeniedHandler(accessDeniedHandler)            // 403
        );

        return http.build();
    }

    // 비밀번호 암호화로 BCrypt 사용 (salt 자동 포함, 단방향 해시)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // DB에서 사용자를 조회하고 비밀번호를 검증하는 인증 제공자 등록
    @Bean
    AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    // 실제 인증 처리
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // SecurityContext를 HTTP 세션에 저장/로드하는 컴포넌트
    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

}
