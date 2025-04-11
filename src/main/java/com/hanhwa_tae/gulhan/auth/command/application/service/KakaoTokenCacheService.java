package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.KakaoRefreshToken;
import com.hanhwa_tae.gulhan.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.gulhan.auth.command.infrastructure.repository.RedisAuthRepository;
import com.hanhwa_tae.gulhan.auth.command.infrastructure.repository.RedisKakaoAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
// 리프레시 토큰을 Redis에 캐시하는 Service
public class KakaoTokenCacheService {

    private final RedisKakaoAuthRepository redisKakaoAuthRepository;

    public void saveKakaoAccessToken(String userId, String refreshToken, long expiresIn) {
        KakaoRefreshToken token
                = KakaoRefreshToken.builder()
                .userId(userId)
                .token(refreshToken)
                .expiresIn(expiresIn)
                .build();

        redisKakaoAuthRepository.save(token);
    }

    public String getRefreshToken(String userId) {
        return redisKakaoAuthRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("리프레시 토큰을 찾을 수 없습니다."))
                .getToken();
    }

}

