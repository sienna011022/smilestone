package com.smilestone.gateway.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorType {

    NOT_FOUND_TOKEN("G001", "토큰이 존재하지 않습니다"),
    UNAUTHORIZED_ROLES("G002","권한이 없습니다"),
    EXPIRED_TOKEN("G003","토큰의 유효기간이 종료되었습니다");

    private final String code;
    private final String message;



}
