package com.smilestone.smarket_api.user.controller.dto;

import com.smilestone.smarket_api.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {

    private String userId;

    private Map<String,String> tokens;
    private List<String> roles;

    private SignInResponse(User user) {
        this.userId = user.getUserId();
        this.roles = user.getRolesName();
    }

    public static SignInResponse createSignInResponse(SignInRequest request, Map<String,String> jwts, List<String> roles) {
        return SignInResponse.builder()
            .userId(request.getUserId())
            .tokens(jwts)
            .roles(roles)
            .build();
    }

    public static SignInResponse signAcceptResponse(User user){
        return new SignInResponse(user);
    }
}
