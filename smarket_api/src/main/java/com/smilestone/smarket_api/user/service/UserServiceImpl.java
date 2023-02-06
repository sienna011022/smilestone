package com.smilestone.smarket_api.user.service;

import com.smilestone.smarket_api.user.common.exception.ExistsUserException;
import com.smilestone.smarket_api.user.common.exception.NotFoundUserException;
import com.smilestone.smarket_api.user.controller.dto.SignInRequest;
import com.smilestone.smarket_api.user.controller.dto.SignInResponse;
import com.smilestone.smarket_api.user.controller.dto.SignUpRequest;
import com.smilestone.smarket_api.user.entity.PasswordFactory;
import com.smilestone.smarket_api.user.entity.User;
import com.smilestone.smarket_api.user.repository.UserRepository;
import com.smilestone.smarket_api.user.entity.JwtFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.smilestone.smarket_api.user.controller.dto.SignInResponse.createSignInResponse;
import static com.smilestone.smarket_api.user.controller.dto.SignInResponse.signAcceptResponse;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordFactory passwordFactory;
    private final JwtFactory jwtProvider;

    @Transactional(value = "masterTransactionManager")
    public void createUser(SignUpRequest request) {
        findUser(request.getUserId());
        User requestUser = request.toUser();
        userRepository.save(requestUser);
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
            .orElseThrow(() -> new Error("아이디가 존재하지 않습니다"));

        passwordFactory.isValid(request.getPassword(), user.getPassword());

        String jwt = jwtProvider.createToken(user.getUserId(), user.getRolesName());
        return createSignInResponse(request, jwt, user.getRolesName());
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
