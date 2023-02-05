package com.smilestone.smarket_api.user.controller.dto;

import com.smilestone.smarket_api.user.entity.Authority;
import com.smilestone.smarket_api.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Collections.singletonList;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    private static final String DEFAULT_ROLE  = "ROLE_USER";

    private String userId;

    private String password;

    private String name;

    private String email;

    @Builder
    private SignUpRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toUser() {
        User newUser = User.builder()
            .userId(userId)
            .password(password)
            .name(name)
            .email(email)
            .build();

        newUser.roles(singletonList(Authority.builder()
            .name(DEFAULT_ROLE)
            .build()));

        return newUser;
    }
}
