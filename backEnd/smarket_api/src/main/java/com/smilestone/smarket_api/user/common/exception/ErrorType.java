package com.smilestone.smarket_api.user.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorType {

    EXISTS_USER("U001", "이미 존재하는 아이디입니다"),
    NOT_FOUND_USER("U002", "존재하지 않는 유저입니다"),
    ILLEGAL_PASSWORD("U003","패스워드가 올바르지 않습니다"),
    ILLEGAL_TOKEN("U004","유효하지 않은 토큰입니다");

    private final String code;
    private final String message;



}
