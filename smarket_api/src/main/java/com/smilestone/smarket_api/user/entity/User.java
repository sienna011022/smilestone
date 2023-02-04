package com.smilestone.smarket_api.user.entity;

import com.smilestone.smarket_api.user.controller.dto.SignInRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static org.springframework.util.Assert.hasText;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {

    @Column(nullable = false, length = 20, unique = true)
    private String userId;

    @Column(length = 64)
    private String password;

    @Column(length = 20)
    private String name;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Builder
    private User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String userId, String password, String name, String email) {
        super(id, createdAt, updatedAt);

        hasText(userId, "아이디를 입력하세요");
        hasText(name, "이름을 입력하세요");
        hasText(password, "비밀번호를 입력하세요");
        hasText(email, "이메일을 입력하세요");

        this.userId = userId;
        this.password = PasswordFactory.encryptPassword(password);
        this.name = name;
        this.email = email;
    }

    public boolean isValid(SignInRequest request){
        return PasswordFactory.isValid(request.getPassword(),this.password);
    }

}
