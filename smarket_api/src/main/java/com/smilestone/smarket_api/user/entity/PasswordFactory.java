package com.smilestone.smarket_api.user.entity;

import com.smilestone.smarket_api.user.common.exception.IllegalPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordFactory {
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String encryptPassword(String password) {
        return PASSWORD_ENCODER.encode(password);
    }

    public static boolean isValid(String inputPassword, String savedPassword) {
        if (!PASSWORD_ENCODER.matches(inputPassword, savedPassword)) {
            throw new IllegalPasswordException();
        }
        return true;
    }
}
