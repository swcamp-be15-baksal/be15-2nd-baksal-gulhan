package com.hanhwa_tae.secondserver.auth.query.service;

import com.hanhwa_tae.secondserver.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.RefreshToken;
import com.hanhwa_tae.secondserver.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.secondserver.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.secondserver.auth.query.dto.response.AccessTokenResponse;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.*;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import com.hanhwa_tae.secondserver.utils.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthQueryServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private AuthQueryServiceImpl authQueryService;

    private final Long userNo = 99L;
    private final String userId = "user99";
    private final String wrongId = "fail99";
    private final String password = "pass99";
    private final String wrongPasssword = "fail00";
    private final String encodedPassword = "encode99";
    private final String email = "toki0327@naver.com";
    private final String accessToken = "accessToken";
    private final String refreshToken = "refreshToken";
    private final Rank rank = new Rank();
    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .userNo(userNo)
                .userId(userId)
                .email(email)
                .gender(GenderType.M)
                .loginType(LoginType.GENERAL)
                .password(encodedPassword)
                .rank(rank)
                .build();
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    void testLoginSuccess() {
        LoginRequest request = LoginRequest.builder()
                .userId(userId)
                .password(password)
                .build();
        /* given */
//         미리 만들어진 유저 정보가 return 되도록 설정
        when(userMapper.findUserByUserId(request.getUserId())).thenReturn(Optional.of(user));

        /* 비밀번호를 비교할 때 true가 return 되도록 설정 */
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        /* 엑세스 토큰 생성 시 생성될 토큰 설정*/
        when(jwtTokenProvider.createAccessToken(any(),any(), any())).thenReturn(accessToken);

        /* 리프레쉬 토큰 생성 시 생성될 토큰 설정*/
        when(jwtTokenProvider.createRefreshToken(any(), any(), any())).thenReturn(refreshToken);

        /* when */
        TokenResponse response = authQueryService.login(request);

        /* then */
        assertEquals(response.getAccessToken(), accessToken);
        assertEquals(response.getRefreshToken(), refreshToken);
    }

    @Test
    @DisplayName("아이디가 틀린 경우")
    void testWrongIdLogin(){
        LoginRequest request = LoginRequest.builder()
                .userId(wrongId)
                .password(password)
                .build();

        BusinessException exception = assertThrows(BusinessException.class,
                () -> authQueryService.login(request));

        assertEquals(ErrorCode.INVALID_LOGIN_REQUEST, exception.getErrorCode());
    }

    @Test
    @DisplayName("비밀번호가 틀린 경우")
    void testWrongPasswordLogin(){
        LoginRequest request = LoginRequest.builder()
                .userId(userId)
                .password(wrongPasssword)
                .build();

        BusinessException exception = assertThrows(BusinessException.class,
                () -> authQueryService.login(request));

        assertEquals(ErrorCode.INVALID_LOGIN_REQUEST, exception.getErrorCode());
    }

    @Test
    @DisplayName("Access Token 재발급")
    void testReissueToken(){
        String request = refreshToken;

        when(jwtTokenProvider.getUserIdFromJWT(refreshToken)).thenReturn(userId);
        when(jwtTokenProvider.getRankFromJWT(refreshToken)).thenReturn(rank.getRankName().getRankName());
        when(jwtTokenProvider.getUserNoFromJWT(refreshToken)).thenReturn(userNo);
        when(jwtTokenProvider.validateToken(refreshToken)).thenReturn(true);
        when(authRepository.findById(userId)).thenReturn(Optional.of(
                RefreshToken.builder().userId(userId).userNo(userNo).token(refreshToken).build()
        ));
        when(jwtTokenProvider.createAccessToken(userId, userNo, rank.getRankName().getRankName())).thenReturn("newAccessToken");

        // when
        AccessTokenResponse response = authQueryService.reissue(request);

        // then
        assertNotNull(response.getAccessToken());
        assertEquals("newAccessToken", response.getAccessToken());

        verify(jwtTokenProvider).createAccessToken(userId, userNo, rank.getRankName().getRankName());
    }
}