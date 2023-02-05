package com.smilestone.smarket_api.user.controller;

import com.smilestone.smarket_api.user.controller.dto.SignInRequest;
import com.smilestone.smarket_api.user.controller.dto.SignInResponse;
import com.smilestone.smarket_api.user.controller.dto.SignUpRequest;
import com.smilestone.smarket_api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequest request) {
        userService.createUser(request);
        return status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest request) {
        return new ResponseEntity<>(userService.signIn(request), HttpStatus.OK);
    }

    @GetMapping("/users/signin")
    public ResponseEntity<SignInResponse> signInWithJWT(@RequestParam String userId) {
        return new ResponseEntity<>(userService.validWithJWT(userId), HttpStatus.OK);
    }


}
