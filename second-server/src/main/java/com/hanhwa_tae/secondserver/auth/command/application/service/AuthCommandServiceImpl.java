package com.hanhwa_tae.secondserver.auth.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {

    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void logout(CustomUserDetail userDetail, String refreshToken) {
        // Access Token이 넘어오지 않은 경우
        if(userDetail == null){
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
        String userDetailUserId = userDetail.getUserId();
        String refreshTokenUserId = jwtTokenProvider.getUserIdFromJWT(refreshToken);

        // 검증 : accessToken에 적힌 Id 값과 refreshToken에 적힌 Id 값이 일치하는 지 확인
        if(!userDetailUserId.equals(refreshTokenUserId)){
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }

        // 1. Refresh Token 유효성 검증
        jwtTokenProvider.validateToken(refreshToken);

        // 2. userId 파싱해서 가져오기!
        String userId = jwtTokenProvider.getUserIdFromJWT(refreshToken);

        // 2.Refresh Token 삭제
        authRepository.deleteById(userId);
    }

}
