package com.malgn.global.exception;

import com.malgn.global.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 비즈니스 로직 예외 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(ApiResponse.fail(e.getMessage()));
    }

    // 잘못된 인자 전송 시 예외 처리(Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Void>> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("잘못된 인자 전송: {}", e.getBindingResult().getFieldError());

        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null)
                ? fieldError.getField() + ": " + fieldError.getDefaultMessage()
                : "올바르지 않은 입력입니다.";

        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.fail(errorMessage));
    }

    // 시큐리티 로그인 실패 (아이디/비밀번호 틀림) 처리
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<ApiResponse<Void>> handleBadCredentialsException(BadCredentialsException e) {
        log.warn("로그인 실패: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED) // 401 에러
                .body(ApiResponse.fail("아이디 또는 비밀번호가 올바르지 않습니다."));
    }

    // 그 외 예상치 못한 모든 에러 처리 (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.fail("서버 내부 오류가 발생했습니다."));
    }
}
