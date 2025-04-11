package com.hanhwa_tae.gulhan.auth.command.domain.repository;

<<<<<<< HEAD
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;

import java.util.Optional;

public interface AuthRepository {
    void deleteById(String token);

    boolean existsById(String userId);

    RefreshToken save(RefreshToken refreshTokenEntity);

    Optional<RefreshToken> findById(String userId);
=======


public interface AuthRepository {
>>>>>>> cd430ab (Feat: kakao 로그인/회원가입 기능 개발 #8)
}
