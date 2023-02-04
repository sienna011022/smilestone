package com.smilestone.smarket_api.user.controller.dto;

import com.smilestone.smarket_api.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequest {

    private String userId;

    private String password;

    private String name;

    private String email;

    @Builder
    public UserCreateRequest(String userId, String password, String name, String email) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User toUser() {
        return User.builder()
            .userId(userId)
            .password(password)
            .name(name)
            .email(email)
            .build();
    }
}
