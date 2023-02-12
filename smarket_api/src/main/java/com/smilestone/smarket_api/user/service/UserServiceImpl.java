package com.smilestone.smarket_api.user.service;

import com.smilestone.smarket_api.user.common.exception.ExistsUserException;
import com.smilestone.smarket_api.user.common.exception.NotFoundUserException;
import com.smilestone.smarket_api.user.controller.dto.SignInRequest;
import com.smilestone.smarket_api.user.controller.dto.SignInResponse;
import com.smilestone.smarket_api.user.controller.dto.SignUpRequest;
import com.smilestone.smarket_api.user.controller.dto.SignUpResponse;
import com.smilestone.smarket_api.user.entity.PasswordFactory;
import com.smilestone.smarket_api.user.entity.User;
import com.smilestone.smarket_api.user.repository.UserRepository;
import com.smilestone.smarket_api.user.entity.JwtFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

import static com.smilestone.smarket_api.user.controller.dto.SignInResponse.createSignInResponse;
import static com.smilestone.smarket_api.user.controller.dto.SignInResponse.signAcceptResponse;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordFactory passwordFactory;
    private final JwtFactory jwtProvider;

    @Transactional(value = "masterTransactionManager")
    public SignUpResponse createUser(SignUpRequest request) {
        findUser(request.getUserId());
        User requestUser = request.toUser();
        User savedUser = userRepository.save(requestUser);
        return SignUpResponse.createSignUpResponse(savedUser);
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
            .orElseThrow(() -> new Error("아이디가 존재하지 않습니다"));

        passwordFactory.isValid(request.getPassword(), user.getPassword());

        Map<String,String> jwts = jwtProvider.generateTokens(user.getUserId(), user.getRolesName());
        return createSignInResponse(request, jwts, user.getRolesName());
    }

    @Override
    public SignInResponse validWithJWT(String userId) {
        User user = userRepository.findByUserId(userId)
            .orElseThrow(() -> new NotFoundUserException());
        return signAcceptResponse(user);
    }

    private void findUser(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new ExistsUserException();
        }
    }

}
