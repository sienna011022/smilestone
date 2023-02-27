package com.smilestone.gateway.exception;

import lombok.Getter;

@Getter
public class UnAuthorizedException extends RuntimeException {
    private final ErrorType errorType;

    public UnAuthorizedException() {
        errorType = ErrorType.UNAUTHORIZED_ROLES;
    }
}
