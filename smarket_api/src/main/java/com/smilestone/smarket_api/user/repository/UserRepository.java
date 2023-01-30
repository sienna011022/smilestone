package com.smilestone.smarket_api.user.repository;

import com.smilestone.smarket_api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByUserId(String memberId);
}
