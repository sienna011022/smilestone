package com.smilestone.smarket_api.user.service;

import com.smilestone.smarket_api.user.controller.dto.*;

import java.util.UUID;

public interface UserService {
    SignUpResponse createUser(SignUpRequest request);

    SignInResponse signIn(SignInRequest request);

    SignInResponse validWithJWT(String userId);

    String updateToken(UUID tokenId);

    UserInfoResponse allInfoBy(Long id);
}
