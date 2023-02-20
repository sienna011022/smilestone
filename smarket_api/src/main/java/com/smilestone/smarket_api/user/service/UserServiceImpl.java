package com.smilestone.smarket_api.user.service;

import com.smilestone.smarket_api.user.common.exception.ExistsUserException;
import com.smilestone.smarket_api.user.common.exception.NotFoundUserException;
import com.smilestone.smarket_api.user.controller.dto.*;
import com.smilestone.smarket_api.user.entity.PasswordFactory;
import com.smilestone.smarket_api.user.entity.User;
import com.smilestone.smarket_api.user.repository.UserRepository;
import com.smilestone.smarket_api.user.entity.JwtFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;
import java.util.UUID;

import static com.smilestone.smarket_api.user.controller.dto.SignInResponse.createSignInResponse;
import static com.smilestone.smarket_api.user.controller.dto.SignInResponse.signAcceptResponse;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final String USER_REPOSITORY_MANAGER = "masterTransactionManager";
    private final UserRepository userRepository;
    private final PasswordFactory passwordFactory;
    private final JwtFactory jwtFactory;


    @Transactional(value = USER_REPOSITORY_MANAGER)
    public SignUpResponse createUser(SignUpRequest request) {
        findUser(request.getUserId());
        User requestUser = request.toUser();
        User savedUser = userRepository.save(requestUser);
        return SignUpResponse.createSignUpResponse(savedUser);
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        User user = userRepository.findByUserId(request.getUserId())
            .orElseThrow(() -> new NotFoundUserException());

        passwordFactory.isValid(request.getPassword(), user.getPassword());

        Map<String, String> jwts = jwtFactory.generateTokens(user.getUserId(), user.getRolesName());
        return createSignInResponse(request, jwts, user);
    }

    @Override
    public SignInResponse validWithJWT(String userId) {
        User user = userRepository.findByUserId(userId)
            .orElseThrow(NotFoundUserException::new);
        return signAcceptResponse(user);
    }

    @Override
    public String updateToken(UUID tokenId) {
        return jwtFactory.validAndUpdateRefreshToken(tokenId);
    }

    @Override
    public UserInfoResponse allInfoBy(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(NotFoundUserException::new);
        return UserInfoResponse.of(user);
    }

    @Override
    public Boolean checkDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public UserInfoResponse changeNickName(String nickName, String newNickName) {
        User user = userRepository.findByNickName(nickName)
            .orElseThrow(NotFoundUserException::new);
        user.changeNickName(newNickName);
        User newUser = userRepository.save(user);
        return UserInfoResponse.of(newUser);
    }

    @Override
    public void changePassword(Long id, String password, String newPassword) {
        User user = userRepository.findById(id)
            .orElseThrow(NotFoundUserException::new);
        passwordFactory.isValid(password, user.getPassword());
        user.changePassword(passwordFactory.encryptPassword(newPassword));
        userRepository.save(user);
    }

    private void findUser(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new ExistsUserException();
        }
    }
}
