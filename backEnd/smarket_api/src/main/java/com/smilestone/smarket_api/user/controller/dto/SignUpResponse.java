package com.smilestone.smarket_api.user.controller.dto;

import com.smilestone.smarket_api.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpResponse {
    private long id;
    private String userId;
    private String nickName;

    @Builder
    private SignUpResponse(Long id, String userId,String nickName) {
        this.id = id;
        this.userId = userId;
        this.nickName = nickName;
    }

    public static SignUpResponse createSignUpResponse(User user){
        return SignUpResponse.builder()
            .id(user.getId())
            .userId(user.getUserId())
            .nickName(user.getNickName())
            .build();
    }

}
