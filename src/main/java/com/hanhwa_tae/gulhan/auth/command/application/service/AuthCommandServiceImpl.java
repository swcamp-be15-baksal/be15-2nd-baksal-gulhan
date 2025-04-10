package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.gulhan.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.gulhan.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void logout(RefreshTokenRequest refreshToken) {
        // 1. Refresh Token 유효성 검증
        jwtTokenProvider.validateToken(refreshToken.getRefreshToken());

        // 2. userId 파싱해서 가져오기!
        String userId = jwtTokenProvider.getUserIdFromJWT(refreshToken.getRefreshToken());

        // 2.Refresh Token 삭제
        authRepository.deleteById(userId);
    }

}
