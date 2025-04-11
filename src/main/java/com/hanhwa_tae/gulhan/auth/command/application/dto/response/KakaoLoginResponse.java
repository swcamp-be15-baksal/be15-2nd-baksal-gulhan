package com.hanhwa_tae.gulhan.auth.command.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoLoginResponse {
    private String userId;
    private String username;
    private boolean needsAdditionalInfo;
    private TokenResponse token;
}
