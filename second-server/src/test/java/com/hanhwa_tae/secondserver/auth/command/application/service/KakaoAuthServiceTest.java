package com.hanhwa_tae.secondserver.auth.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.application.dto.request.KakaoAdditionalInfoRequest;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoLoginResponse;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoUserResponse;
import com.hanhwa_tae.secondserver.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.secondserver.auth.command.infrastructure.repository.RedisKakaoAuthRepository;
import com.hanhwa_tae.secondserver.common.domain.DeleteType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.*;
import com.hanhwa_tae.secondserver.user.command.domain.repository.RankRepository;
import com.hanhwa_tae.secondserver.user.command.domain.repository.UserInfoRepository;
import com.hanhwa_tae.secondserver.user.command.domain.repository.UserRepository;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import com.hanhwa_tae.secondserver.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KakaoAuthServiceTest {

    @InjectMocks
    private KakaoAuthService kakaoAuthService;

    @Mock private KakaoAuthProvider kakaoAuthProvider;
    @Mock private JwtTokenProvider jwtTokenProvider;
    @Mock private AuthRepository authRepository;
    @Mock private RedisKakaoAuthRepository redisKakaoAuthRepository;
    @Mock private UserRepository userRepository;
    @Mock private UserInfoRepository userInfoRepository;
    @Mock private RankRepository rankRepository;
    @Mock private UserMapper userMapper;

    @Test
    void testKakaoLogin() {
        // given
        String code = "auth_code";
        String kakaoAccessToken = "KAKAO_ACCESS_TOKEN";
        String kakaoRefreshToken = "KAKAO_REFRESH_TOKEN";
        String userId = "kakao_12345";
        String nickname = "테스트유저";

        KakaoTokenResponse kakaoToken = KakaoTokenResponse.builder()
                .accessToken(kakaoAccessToken)
                .refreshToken(kakaoRefreshToken)
                .refreshTokenExpiresIn(99999L)
                .build();

        Map<String, Object> props = new HashMap<>();
        props.put("nickname", nickname);

        KakaoUserResponse kakaoUser = Mockito.mock(KakaoUserResponse.class);
        Mockito.when(kakaoUser.getId()).thenReturn(Long.valueOf("12345"));
        Mockito.when(kakaoUser.getProperties()).thenReturn(props);

        Rank rank = Rank.builder()
                .rankName(RankType.COMMONER)
                .build();

        User user = User.builder()
                .userId(userId)
                .username(nickname)
                .rank(rank)
                .loginType(LoginType.KAKAO)
                .isDeleted(DeleteType.N)
                .build();
        user.setUserNo(1L);

        when(kakaoAuthProvider.getAccessToken(code)).thenReturn(kakaoToken);
        when(kakaoAuthProvider.getUserInfo(kakaoAccessToken)).thenReturn(kakaoUser);
        when(userMapper.findUserByUserId(userId)).thenReturn(Optional.empty());
        when(rankRepository.findByRankName(RankType.COMMONER)).thenReturn(rank);
        when(userRepository.save(any(User.class))).thenReturn(user);

        when(jwtTokenProvider.createAccessToken(eq(userId), eq(1L), eq(RankType.COMMONER.name())))
                .thenReturn("JWT_ACCESS_TOKEN");
        when(jwtTokenProvider.createRefreshToken(eq(userId), eq(1L), eq(RankType.COMMONER.name())))
                .thenReturn("JWT_REFRESH_TOKEN");

        when(userInfoRepository.findByUserNo(1L)).thenReturn(null);

        // when
        KakaoLoginResponse response = kakaoAuthService.login(code);

        // then
        assertEquals(userId, response.getUserId());
        assertEquals(nickname, response.getUsername());
        assertTrue(response.isNeedsAdditionalInfo());
        assertEquals("JWT_ACCESS_TOKEN", response.getToken().getAccessToken());
    }

    @Test
    void testUpdateKakaoUserInfo() {
        // given
        String userId = "kakao_12345";
        long userNo = 100L;

        KakaoAdditionalInfoRequest request = Mockito.mock(KakaoAdditionalInfoRequest.class);
        when(request.getUserId()).thenReturn(userId);
        when(request.getEmail()).thenReturn("test@example.com");
        when(request.getBirth()).thenReturn("2000-12-19");
        when(request.getPhone()).thenReturn("010-1234-5678");
        when(request.getAddress()).thenReturn("서울특별시 관악구 봉천동");

        User user = Mockito.mock(User.class);
        when(user.getUserNo()).thenReturn(userNo);

        when(userRepository.findUserByUserId(userId)).thenReturn(Optional.of(user));
        when(userInfoRepository.findByUserNo(userNo)).thenReturn(null);

        // when
        kakaoAuthService.updateKakaoUserInfo(request);

        // then
        verify(user).setEmail("test@example.com");
        verify(userInfoRepository).save(any(UserInfo.class));
    }

    @Test
    void testUpdateTokenByKakao() {
        // given
        String refreshToken = "KAKAO_REFRESH_TOKEN";
        KakaoTokenResponse response = KakaoTokenResponse.builder()
                .accessToken("NEW_KAKAO_ACCESS_TOKEN")
                .refreshToken("NEW_KAKAO_REFRESH_TOKEN")
                .build();

        when(kakaoAuthProvider.requestNewToken(refreshToken)).thenReturn(response);

        // when
        KakaoTokenResponse result = kakaoAuthService.refreshTokenByKakao(refreshToken);

        // then
        assertEquals("NEW_KAKAO_ACCESS_TOKEN", result.getAccessToken());
    }

    @Test
    void testKakaoLogout() {
        // given
        String userId = "kakao_12345";
        String accessToken = "ACESS_TOKEN";
        String kakaoRefreshToken = "KAKAO_REFRESH_TOKEN";

        // when
        kakaoAuthService.logout(userId, accessToken, kakaoRefreshToken);

        // then
        verify(kakaoAuthProvider).logout(accessToken);
        verify(authRepository).deleteById(userId);
        verify(redisKakaoAuthRepository).deleteById(kakaoRefreshToken);
    }
}

