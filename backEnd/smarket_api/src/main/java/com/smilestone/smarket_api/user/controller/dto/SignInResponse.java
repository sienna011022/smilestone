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

    private Long id;
    private String userId;

    private Map<String, String> tokens;
    private List<String> roles;

    private SignInResponse(User user) {
        this.userId = user.getUserId();
        this.roles = user.getRolesName();
        this.id = user.getId();
    }

    public static SignInResponse createSignInResponse(SignInRequest request, Map<String, String> jwts, User user) {
        return SignInResponse.builder()
            .id(user.getId())
            .userId(request.getUserId())
            .tokens(jwts)
            .roles(user.getRolesName())
            .build();
    }

    public static SignInResponse signAcceptResponse(User user) {
        return new SignInResponse(user);
    }
}
