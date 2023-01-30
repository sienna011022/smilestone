package com.smilestone.smarket_api.user;

import com.smilestone.smarket_api.user.controller.dto.UserCreateRequest;
import com.smilestone.smarket_api.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MemberTest {

    @Test
    @DisplayName("회원가입을 한다")
    void create_user() {
        UserCreateRequest request = UserCreateRequest.builder()
            .userId("sienna1022")
            .name("kimSungYoon")
            .email("sienna011022@naver.com")
            .password("abcd")
            .build();

        Assertions.assertThat(request.toUser()).isInstanceOf(User.class);
    }

    @Test
    @DisplayName("아이디를 입력하지 않으면 예외 발생")
    void create_user_exception(){
        UserCreateRequest errorRequest = UserCreateRequest.builder()
            .userId(null)
            .name("kimSungYoon")
            .email("sienna011022@naver.com")
            .password("abcd")
            .build();

        assertThatThrownBy(() -> errorRequest.toUser()).isInstanceOf(IllegalArgumentException.class);
    }


}
