package com.smilestone.smarket_api.user.service;

import com.smilestone.smarket_api.user.controller.dto.SignInRequest;
import com.smilestone.smarket_api.user.controller.dto.SignInResponse;
import com.smilestone.smarket_api.user.controller.dto.SignUpRequest;

public interface UserService {
    void createUser(SignUpRequest request);

    SignInResponse signIn(SignInRequest request);

    SignInResponse validWithJWT(String userId);
}
