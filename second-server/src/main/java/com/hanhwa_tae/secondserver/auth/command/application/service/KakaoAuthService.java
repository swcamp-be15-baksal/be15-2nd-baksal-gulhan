package com.hanhwa_tae.secondserver.auth.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.application.dto.request.KakaoAdditionalInfoRequest;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoLoginResponse;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoUserResponse;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.KakaoRefreshToken;
import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.RefreshToken;
import com.hanhwa_tae.secondserver.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.secondserver.auth.command.infrastructure.repository.RedisKakaoAuthRepository;
import com.hanhwa_tae.secondserver.common.domain.DeleteType;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.LoginType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.RankType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.UserInfo;
import com.hanhwa_tae.secondserver.user.command.domain.repository.RankRepository;
import com.hanhwa_tae.secondserver.user.command.domain.repository.UserInfoRepository;
import com.hanhwa_tae.secondserver.user.command.domain.repository.UserRepository;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import com.hanhwa_tae.secondserver.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class KakaoAuthService {

    private final KakaoAuthProvider kakaoAuthProvider;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;
    private final RedisKakaoAuthRepository redisKakaoAuthRepository;

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final RankRepository rankRepository;
    private final UserMapper userMapper;

    @Transactional
    public KakaoLoginResponse login(String code) {

        // 인가 코드로 토큰 발급
        KakaoTokenResponse kakaoToken = kakaoAuthProvider.getAccessToken(code);

        // 카카오에서 받은 엑세스 토큰으로 카카오 유저 정보를 가져옴
        KakaoUserResponse kakaoUser = kakaoAuthProvider.getUserInfo(kakaoToken.getAccessToken());

        // 백엔드 관리자 확인용 로그
        log.info("카카오 access token: {}", kakaoToken.getAccessToken());
        log.info("카카오 refresh token: {}", kakaoToken.getRefreshToken());

        String userId = "kakao_" + kakaoUser.getId();
        String nickname = (String) kakaoUser.getProperties().get("nickname");

        // DB에 유저 존재 여부 확인 후 없으면 회원가입 처리
        User user = userMapper.findUserByUserId(userId)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .userId(userId)
                            .username(nickname)
                            .rank(rankRepository.findByRankName(RankType.COMMONER))
                            .loginType(LoginType.KAKAO)
                            .isDeleted(DeleteType.N)
                            .build();
                    return userRepository.save(newUser);
                });

        // 카카오 리프레시 토큰 저장
        KakaoRefreshToken refreshToken = KakaoRefreshToken.builder()
                .userId(userId)
                .token(kakaoToken.getRefreshToken())
                .expiresIn(kakaoToken.getRefreshTokenExpiresIn())
                .build();
        redisKakaoAuthRepository.save(refreshToken);

        // 우리 서비스에서 쓰일 JWT 엑세스 토큰, 리프레시 토큰 발급
        Long userNo = user.getUserNo();
        RankType rank = user.getRank().getRankName();
        String jwtAccessToken = jwtTokenProvider.createAccessToken(userId, userNo, rank.name());
        String jwtRefreshToken = jwtTokenProvider.createRefreshToken(userId, userNo, rank.name());

        // Redis에 JWT 리프레시 토큰 저장
        authRepository.save(
                RefreshToken.builder()
                        .userId(userId)
                        .token(jwtRefreshToken)
                        .build()
        );

        // 추가 정보 입력 여부
        boolean needsAdditionalInfo = user.getRank().getRankName() != RankType.SLAVE
                && userInfoRepository.findByUserNo(userNo) == null;

        // 유저 정보 응답
        return KakaoLoginResponse.builder()
                .userId(userId)
                .username(nickname)
                .needsAdditionalInfo(needsAdditionalInfo)
                .token(TokenResponse.builder()
                        .accessToken(jwtAccessToken)
                        .refreshToken(jwtRefreshToken)
                        .build())
                .build();

    }

    @Transactional
    public void updateKakaoUserInfo(KakaoAdditionalInfoRequest request) {
        User user = userRepository.findUserByUserId(request.getUserId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (userInfoRepository.findByUserNo(user.getUserNo()) != null) {
            throw new BusinessException(ErrorCode.ALREADY_ADDITIONAL_INFO_EXISTS);
        }

        user.setEmail(request.getEmail());

        // 생년월일 String → Date 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedBirth = LocalDate.parse(request.getBirth(), formatter);
        Date birthDate = Date.from(parsedBirth.atStartOfDay(ZoneId.systemDefault()).toInstant());

        UserInfo userInfo = UserInfo.builder()
                .user(user)
                .phone(request.getPhone())
                .birth(birthDate)
                .address(request.getAddress())
                .build();

        userInfoRepository.save(userInfo);
    }

    @Transactional
    public KakaoTokenResponse refreshTokenByKakao(String refreshToken) {
        log.info("카카오 토큰 갱신 호출, refresh token: {}", refreshToken);
        return kakaoAuthProvider.requestNewToken(refreshToken);
    }

    @Transactional
    public void logout(String userId, String accessToken, String kakaoRefreshToken) {
            kakaoAuthProvider.logout(accessToken);  // 카카오 로그아웃
            authRepository.deleteById(userId); // JWT 리프레시 토큰 삭제
            redisKakaoAuthRepository.deleteById(kakaoRefreshToken); // 카카오 리프레시 토큰 삭ㅈ
    }

    @Transactional
    public void withdraw(String userId, String accessToken) {
        User user = userMapper.findUserByUserId(userId)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if (user.getIsDeleted() == DeleteType.Y) {
            throw new BusinessException(ErrorCode.USER_ALREADY_DELETED);
        }

        if (user.getLoginType() == LoginType.KAKAO) {
            kakaoAuthProvider.unlink(accessToken);
        }

        user.setIsDeleted(DeleteType.Y);

        authRepository.deleteById(userId);
        redisKakaoAuthRepository.findById(userId).ifPresent(token ->
                redisKakaoAuthRepository.deleteById(token.getToken())
        );
    }



}
