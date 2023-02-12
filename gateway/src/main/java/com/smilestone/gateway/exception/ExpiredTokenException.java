package com.smilestone.gateway.exception;

import lombok.Getter;

@Getter
public class ExpiredTokenException extends RuntimeException {
    private final ErrorType errorType;

    public ExpiredTokenException(){
        errorType = ErrorType.EXPIRED_TOKEN;
    }
}
