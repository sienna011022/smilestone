package com.smilestone.smarket_api.user.service;

import com.smilestone.smarket_api.user.common.exception.ExistsUserException;
import com.smilestone.smarket_api.user.controller.dto.UserCreateRequest;
import com.smilestone.smarket_api.user.entity.User;
import com.smilestone.smarket_api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserCreateRequest request) {
        findUser(request.getUserId());
        User requestUser = request.toUser();
        userRepository.save(requestUser);
    }

    @Transactional(readOnly = true)
    public void findUser(String userId) {
        if (userRepository.existsByUserId(userId)) {
            throw new ExistsUserException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
