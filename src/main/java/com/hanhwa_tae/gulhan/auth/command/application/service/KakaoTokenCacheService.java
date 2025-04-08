package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.KakaoRefreshToken;
import com.hanhwa_tae.gulhan.auth.command.infrastructure.repository.RedisAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 리프레시 토큰을 Redis에 캐시하는 Service
public class KakaoTokenCacheService {

    private final RedisAuthRepository redisAuthRepository;

    public void saveKakaoAccessToken(String userId, String refreshToken, long expiresIn) {
        KakaoRefreshToken token
                = KakaoRefreshToken.builder()
                .userId(userId)
                .refreshToken(refreshToken)
                .expiresIn(expiresIn)
                .build();

        redisAuthRepository.save(token);
    }

}

