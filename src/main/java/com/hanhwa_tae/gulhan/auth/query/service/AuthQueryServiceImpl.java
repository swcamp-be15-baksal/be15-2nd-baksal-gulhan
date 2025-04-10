package com.hanhwa_tae.gulhan.auth.query.service;


import com.hanhwa_tae.gulhan.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.RefreshToken;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.gulhan.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.gulhan.auth.query.dto.response.AccessTokenResponse;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;

import com.hanhwa_tae.gulhan.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthQueryServiceImpl implements AuthQueryService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;

    @Override
    public TokenResponse login(LoginRequest request) {

        // 1. DB에서 유저 정보 존재 확인 (user 도메인에서 확인하기)

        User foundUser = userMapper.findUserByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다."));
        // 2. 유저 정보 일치 확인하기
        if (!passwordEncoder.matches(request.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }

        /* 여기부터 로그인 성공 */

        // 만약 로그인 된 상태에서 로그인을 요청한다면?
        // ! 기존 refresh 토큰 만료시키고, 새롭게 토큰 발급해주기
        if(authRepository.existsById(foundUser.getUserId())){
            authRepository.deleteById(foundUser.getUserId());
        }

        // 3. access 토큰 발급
        String accessToken = jwtTokenProvider.createAccessToken(foundUser.getUserId(), foundUser.getRank().getRankName().name());

        // 4. refresh 토큰 발급
        String refreshToken = jwtTokenProvider.createRefreshToken(foundUser.getUserId(), foundUser.getRank().getRankName().name());

        // 5. db에 userId:refreshToken 형태로 저장
        RefreshToken refreshTokenEntity = RefreshToken
                .builder()
                .userId(foundUser.getUserId())
                .token(refreshToken)
                .build();

        authRepository.save(refreshTokenEntity);
        // 6. 사용자에게 토큰 전달
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public AccessTokenResponse reissue(CustomUserDetail userDetail, RefreshTokenRequest request) {
//
//        if(userDetail == null){
//            throw new BusinessException(ErrorCode.INVALID_TOKEN);
//        }

        String requestRefreshToken = request.getRefreshToken();
        log.info("유저 ID : " + jwtTokenProvider.getUserIdFromJWT(requestRefreshToken));
        log.info("유저 RANK : " + jwtTokenProvider.getUserIdFromJWT(requestRefreshToken));
        // refreshtoken 검증
        jwtTokenProvider.validateToken(requestRefreshToken);

        String userId = jwtTokenProvider.getUserIdFromJWT(requestRefreshToken);
        String rank = jwtTokenProvider.getRankFromJWT(requestRefreshToken);

        // 1. Redis에서 refresh 토큰 존재 확인
        RefreshToken storedRefreshToken = authRepository.findById(userId).orElseThrow(
                // if 존재하지 않을 경우
                ()-> new BusinessException(ErrorCode.REFRESH_TOKEN_EXPIRATION)
        );

        // ! 리프레쉬 토큰 비교
        if(!storedRefreshToken.getToken().equals(requestRefreshToken)){
            throw new BusinessException(ErrorCode.REFRESH_TOKEN_EXPIRATION);
        }

        // 2. access 토큰 재 발행
        String accessToken = jwtTokenProvider.createAccessToken(
                userId,
                rank
        );

        // 3. 응답
        return AccessTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
