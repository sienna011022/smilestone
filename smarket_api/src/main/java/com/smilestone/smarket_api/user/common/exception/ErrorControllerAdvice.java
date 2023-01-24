package com.smilestone.smarket_api.user.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(NotFoundUserException.class)
    protected ResponseEntity<ErrorResponse> notFoundException(NotFoundUserException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(exception.getErrorType()));
    }

    @ExceptionHandler(IllegalPasswordException.class)
    protected ResponseEntity<ErrorResponse> passwordException(IllegalPasswordException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(exception.getErrorType()));
    }

    @ExceptionHandler(ExistsUserException.class)
    protected ResponseEntity<ErrorResponse> existsUserException(ExistsUserException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse.of(exception.getErrorType()));
    }
}
