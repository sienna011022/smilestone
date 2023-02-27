package com.smilestone.smarket_api.user;

import com.smilestone.smarket_api.user.controller.dto.SignUpRequest;
import com.smilestone.smarket_api.user.entity.User;

public class UserFixture {

    public static SignUpRequest userCreateRequest() {
        SignUpRequest request = SignUpRequest.builder()
            .userId("sienna1022")
            .nickName("kimSungYoon")
            .password("1234")
            .build();

        return request;
    }

    public static User userCreateWithPassword(String password) {
        User request = User.builder()
            .userId("sienna1022")
            .nickName("kimSungYoon")
            .password(password)
            .build();

        return request;
    }

}

