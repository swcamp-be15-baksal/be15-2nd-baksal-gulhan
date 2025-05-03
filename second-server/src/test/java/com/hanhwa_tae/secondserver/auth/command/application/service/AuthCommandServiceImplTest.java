package com.hanhwa_tae.secondserver.auth.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.RankType;
import com.hanhwa_tae.secondserver.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthCommandServiceImplTest {
    @Mock
    private AuthRepository authRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    AuthCommandServiceImpl authCommandService;

    private final String refreshToken = "refreshToken";
    private final String accessToken = "accessToken";
    private final Long userNo = 99L;
    private final String userId = "user99";
    private final String password = "pass99";
    private final RankType rank = RankType.COMMONER;

    private CustomUserDetail userDetail;

    @BeforeEach
    /* 정상적으로 Access Token이 파싱된 상태*/
    void setUp(){
        userDetail = CustomUserDetail.builder()
                .userNo(userNo)
                .userId(userId)
                .authorities(Collections.singleton(new SimpleGrantedAuthority(rank.getRankName())))
                .build();
    }

    @Test
    @DisplayName("로그아웃 성공")
    void testSuccessLogout(){
        // given
        String request = refreshToken;
        /* 토큰 파싱 */
        when(jwtTokenProvider.getUserIdFromJWT(refreshToken)).thenReturn(userId);
        when(jwtTokenProvider.validateToken(request)).thenReturn(true);

        // when
        authCommandService.logout(userDetail, request);

        // then
        verify(authRepository).deleteById(userId);
    }
}