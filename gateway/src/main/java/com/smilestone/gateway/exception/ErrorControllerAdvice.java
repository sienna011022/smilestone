package com.smilestone.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(NotFoundTokenException.class)
    protected ResponseEntity<ErrorResponse> notFoundToken(NotFoundTokenException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(exception.getErrorType()));
    }

    @ExceptionHandler(UnAuthorizedException.class)
    protected ResponseEntity<ErrorResponse> unauthorized(UnAuthorizedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse.of(exception.getErrorType()));
    }

}
