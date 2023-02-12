package com.smilestone.smarket_api.user;

import com.smilestone.smarket_api.user.entity.PasswordFactory;
import com.smilestone.smarket_api.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordFactoryTest {

    private static final int LENGTH_OF_ENCODE = 60;

    @Test
    void encrypt() {
        String result = PasswordFactory.encryptPassword("1234");
        Assertions.assertThat(result.length()).isEqualTo(LENGTH_OF_ENCODE);
    }

    @Test
    void isValid() {
        String inputPassword = "1234";
        User user = UserFixture.userCreateWithPassword(inputPassword);
        PasswordFactory.isValid(inputPassword, user.getPassword());
    }
}
