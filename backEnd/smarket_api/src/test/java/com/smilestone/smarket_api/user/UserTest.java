package com.smilestone.smarket_api.user;

import com.smilestone.smarket_api.user.controller.dto.SignUpRequest;
import com.smilestone.smarket_api.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {

    @Test
    @DisplayName("회원가입을 한다")
    void create_user() {
        SignUpRequest request = SignUpRequest.builder()
            .userId("sienna1022")
            .nickName("kimSungYoon")
            .password("abcd")
            .build();

        Assertions.assertThat(request.toUser()).isInstanceOf(User.class);
    }

    @Test
    @DisplayName("아이디를 입력하지 않으면 예외 발생")
    void create_user_exception(){
        SignUpRequest errorRequest = SignUpRequest.builder()
            .userId(null)
            .nickName("kimSungYoon")
            .password("abcd")
            .build();

        assertThatThrownBy(() -> errorRequest.toUser()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("회원가입시 권한은 ROLE_USER로 설정된다")
    void role_is_user(){
        SignUpRequest request = UserFixture.userCreateRequest();
        User newUser = request.toUser();
        List<String> rolesName = Arrays.asList("ROLE_USER");
        assertThat(newUser.getRolesName()).isEqualTo(rolesName);
    }


}
