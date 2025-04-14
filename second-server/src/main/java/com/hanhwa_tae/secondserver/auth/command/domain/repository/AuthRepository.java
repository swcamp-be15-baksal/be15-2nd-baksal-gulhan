package com.hanhwa_tae.secondserver.auth.command.domain.repository;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.RefreshToken;

import java.util.Optional;

public interface AuthRepository {
    void deleteById(String userId);

    boolean existsById(String userId);

    RefreshToken save(RefreshToken refreshTokenEntity);

    Optional<RefreshToken> findById(String userId);

}
