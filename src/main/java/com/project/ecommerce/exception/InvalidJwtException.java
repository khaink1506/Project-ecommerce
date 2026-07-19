package com.project.ecommerce.exception;

import com.project.ecommerce.enums.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidJwtException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidJwtException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
