package com.hanhwa_tae.gulhan.auth.query.service;


import com.hanhwa_tae.gulhan.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;
import com.hanhwa_tae.gulhan.auth.command.infrastructure.repository.RedisAuthRepository;
import com.hanhwa_tae.gulhan.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;

import com.hanhwa_tae.gulhan.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthQueryServiceImpl implements AuthQueryService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisAuthRepository redisAuthRepository;

    @Override
    public TokenResponse login(LoginRequest request) {

        // 1. DB에서 유저 정보 존재 확인 (user 도메인에서 확인하기)

        User foundUser = userMapper.findUserByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다."));
        // 2. 유저 정보 일치 확인하기
        if (passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        /* 여기부터 로그인 성공 */
        // 3. access 토큰 발급
        String accessToken = jwtTokenProvider.createAccessToken(foundUser.getUserId(), foundUser.getRank().getRankName());

        // 4. refresh 토큰 발급
        String refreshToken = jwtTokenProvider.createRefreshToken(foundUser.getUserId(), foundUser.getRank().getRankName());

        // 5. db에 userId:refreshToken 형태로 저장
        RefreshToken refreshTokenEntity = RefreshToken
                .builder()
                .userId(foundUser.getUserId())
                .token(refreshToken)
                .build();

        redisAuthRepository.save(refreshTokenEntity);
        // 6. 사용자에게 토큰 전달
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }
}
