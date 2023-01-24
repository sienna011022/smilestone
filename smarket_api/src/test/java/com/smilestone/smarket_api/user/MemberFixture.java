package com.smilestone.smarket_api.user;

import com.smilestone.smarket_api.user.controller.dto.SignInRequest;
import com.smilestone.smarket_api.user.controller.dto.UserCreateRequest;
import com.smilestone.smarket_api.user.entity.User;

public class MemberFixture {

    public static UserCreateRequest userCreateRequest() {
        UserCreateRequest request = UserCreateRequest.builder()
            .userId("sienna1022")
            .name("kimSungYoon")
            .email("sienna011022@naver.com")
            .password("1234")
            .build();

        return request;
    }

    public static User userCreateWithPassword(String password) {
        User request = User.builder()
            .userId("sienna1022")
            .name("kimSungYoon")
            .email("sienna011022@naver.com")
            .password(password)
            .build();

        return request;
    }

    public static User createUserByUserId(String userId) {
        User request = User.builder()
            .userId(userId)
            .name("kimSungYoon")
            .email("sienna011022@naver.com")
            .password("password")
            .build();

        return request;
    }


    public static SignInRequest createSignInRequest() {
        SignInRequest request = SignInRequest.builder()
            .memberId("sienna1022")
            .password("abcd")
            .build();
        return request;
    }
}

