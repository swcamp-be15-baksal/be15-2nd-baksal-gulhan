package com.hanhwa_tae.gulhan.auth.command.domain.repository;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;

import java.util.Optional;

public interface AuthRepository {
    void deleteById(String token);

    boolean existsById(String userId);

    RefreshToken save(RefreshToken refreshTokenEntity);

    Optional<RefreshToken> findById(String userId);
}
