package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoLoginResponse;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoUserResponse;
import com.hanhwa_tae.gulhan.auth.command.application.dto.request.KakaoTokenRequest;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.LoginType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.RankType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.command.domain.repository.RankRepository;
import com.hanhwa_tae.gulhan.user.command.domain.repository.UserRepository;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final KakaoAuthProvider kakaoAuthProvider;
    private final UserRepository userRepository;
    private final RankRepository rankRepository;
    private final KakaoTokenCacheService kakaoTokenCacheService;
    private final UserMapper userMapper;

    @Transactional
    public KakaoLoginResponse getKakaoToken(String code) {

        // 1. 인가 코드로 토큰 발급
        KakaoTokenResponse response = kakaoAuthProvider.getAccessToken(code);
        String accessToken = response.getAccessToken();

        // 2. 토큰으로 카카오 유저 정보 가져옴
        KakaoUserResponse userInfo = kakaoAuthProvider.getUserInfo(accessToken);

        String userId = "kakao_" + userInfo.getId();
        String nickname = (String) userInfo.getProperties().get("nickname");

        // 3. DB에 유저 존재 여부 확인
        Optional<User> user = userMapper.findUserByUserId(userId);

        if (user.isEmpty()) {     // 추후 UserNotFoundException 추가 예정 (회원 가입 -> 추가 정보 입력 페이지)
            User newUser = User.builder()
                    .userId(userId)
                    .userName(nickname)
                    .rank(rankRepository.findByRankName(RankType.COMMONER))
                    .loginType(LoginType.KAKAO)
                    .build();
            userRepository.save(newUser);
        }

        // 4. Redis에 refresh token 저장 (회원가입 여부랑 관계 X)
        kakaoTokenCacheService.saveKakaoAccessToken(
                userId, response.getRefreshToken(), response.getExpiresIn()
        );

        return KakaoLoginResponse.builder()
                .userId(userId)
                .username(nickname)
                .token(
                        KakaoTokenRequest.builder()
                                .accessToken(accessToken)
                                .refreshToken(response.getRefreshToken())
                                .build()
                )
                .build();

    }

    @Transactional
    public KakaoTokenResponse getRefreshToken(String userId) {
        return kakaoAuthProvider.getValidToken(userId);
    }


}
