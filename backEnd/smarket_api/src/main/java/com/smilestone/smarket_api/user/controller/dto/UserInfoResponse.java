package com.smilestone.smarket_api.user.controller.dto;

import com.smilestone.smarket_api.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private long id;
    private String userId;
    private String nickName;

    @Builder
    public UserInfoResponse(long id, String userId, String nickName) {
        this.id = id;
        this.userId = userId;
        this.nickName = nickName;
    }

    public static UserInfoResponse of(User user){
        return UserInfoResponse.builder()
            .id(user.getId())
            .userId(user.getUserId())
            .nickName(user.getNickName())
            .build();
    }
}
