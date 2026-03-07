package com.malgn.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

// 공통 비즈니스 예외
@Getter
public class BusinessException extends RuntimeException {
    private final HttpStatus httpStatus;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getStatus();
    }

}
