package com.smilestone.smarket_api.user.common.exception;

import lombok.Getter;


@Getter
public class IllegalRefreshTokenException extends RuntimeException {
    private final ErrorType errorType;

    public IllegalRefreshTokenException() {
        this.errorType = ErrorType.ILLEGAL_TOKEN;
    }
}

