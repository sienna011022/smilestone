package com.smilestone.smarket_api.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SignInRequest {

    private String userId;
    private String password;

    @Builder
    private SignInRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
