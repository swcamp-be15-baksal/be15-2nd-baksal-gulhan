package com.hanhwa_tae.gulhan.auth.command.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoTokenRequest {
    private String accessToken;
    private String refreshToken;
}
