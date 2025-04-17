package com.hanhwa_tae.secondserver.auth.command.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoLoginResponse {
    private String userId;
    private String username;
    private TokenResponse token;
    private boolean needsAdditionalInfo;    // 추가 정보 입력이 필요한 회원
}
