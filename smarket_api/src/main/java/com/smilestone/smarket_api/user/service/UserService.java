package com.smilestone.smarket_api.user.service;

import com.smilestone.smarket_api.user.controller.dto.SignInRequest;
import com.smilestone.smarket_api.user.controller.dto.SignInResponse;
import com.smilestone.smarket_api.user.controller.dto.SignUpRequest;
import com.smilestone.smarket_api.user.controller.dto.SignUpResponse;

import java.util.UUID;

public interface UserService {
    SignUpResponse createUser(SignUpRequest request);

    SignInResponse signIn(SignInRequest request);

    SignInResponse validWithJWT(String userId);

    String updateToken(UUID tokenId);
}
