package com.smilestone.gateway.exception;

import lombok.Getter;

@Getter
public class NotFoundTokenException extends RuntimeException {
    private final ErrorType errorType;

    public NotFoundTokenException(){
        errorType = ErrorType.NOT_FOUND_TOKEN;
    }
}
