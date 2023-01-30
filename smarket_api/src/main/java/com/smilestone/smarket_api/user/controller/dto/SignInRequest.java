package com.smilestone.smarket_api.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignInRequest {

    private String memberId;
    private String password;

    @Builder
    public SignInRequest(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
