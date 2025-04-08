package com.hanhwa_tae.gulhan.auth.command.domain.repository;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;

public interface AuthRepository {
    void deleteById(String token);

    boolean existsById(String userId);

    RefreshToken save(RefreshToken refreshTokenEntity);
}
