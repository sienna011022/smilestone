package com.smilestone.smarket_api.user.repository;

import com.smilestone.smarket_api.user.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    @Override
    Optional<Token> findById(UUID uuid);
}
