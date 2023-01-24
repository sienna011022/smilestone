package com.smilestone.smarket_api.user.common.exception;

import lombok.Getter;

@Getter
public class NotFoundUserException extends RuntimeException {
    private final ErrorType errorType;

    public NotFoundUserException() {
        this.errorType = ErrorType.NOT_FOUND_USER;
    }
}
