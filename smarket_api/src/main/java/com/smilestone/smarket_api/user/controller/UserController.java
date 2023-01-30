package com.smilestone.smarket_api.user.controller;

import com.smilestone.smarket_api.user.controller.dto.UserCreateRequest;
import com.smilestone.smarket_api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")

public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
        return status(HttpStatus.CREATED).build();
    }

}
