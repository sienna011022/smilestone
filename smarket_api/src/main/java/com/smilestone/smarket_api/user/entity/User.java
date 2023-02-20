package com.smilestone.smarket_api.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.Assert.hasText;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {

    @Column(nullable = false, length = 20, unique = true)
    private String userId;

    @Column(length = 64)
    private String password;

    @Column(length = 20)
    private String nickName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Authority> roles = new ArrayList<>();

    @Builder
    private User(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, String userId, String password, String nickName, List<Authority> roles) {
        super(id, updatedAt, createdAt);

        hasText(userId, "아이디를 입력하세요");
        hasText(nickName, "이름을 입력하세요");
        hasText(password, "비밀번호를 입력하세요");

        this.userId = userId;
        this.password = PasswordFactory.encryptPassword(password);
        this.nickName = nickName;
        this.roles = roles;
    }

    public void roles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(role -> role.user(this));
    }

    public List<String> getRolesName() {
        List<String> roleNames = new ArrayList<>();
        roles.stream()
            .forEach(role -> roleNames.add(role.getName()));
        return roleNames;
    }

}
