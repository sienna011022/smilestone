package com.smilestone.smarket_api.user.controller.dto;

import com.smilestone.smarket_api.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    private String userId;

    private String token;
    private List<String> roles;

    private SignInResponse(User user) {
        this.userId = user.getUserId();
        this.roles = user.getRolesName();
    }

    public static SignInResponse createSignInResponse(SignInRequest request, String jwt, List<String> roles) {
        return SignInResponse.builder()
            .userId(request.getUserId())
            .token(jwt)
            .roles(roles)
            .build();
    }

    public static SignInResponse signAcceptResponse(User user){
        return new SignInResponse(user);
    }
}
