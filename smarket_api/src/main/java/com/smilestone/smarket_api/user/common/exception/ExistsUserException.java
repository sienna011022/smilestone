package com.smilestone.smarket_api.user.common.exception;

import lombok.Getter;

@Getter
public class ExistsUserException extends RuntimeException{

    private final ErrorType errorType;

    public ExistsUserException() {
        this.errorType = ErrorType.EXISTS_USER;
    }
}
