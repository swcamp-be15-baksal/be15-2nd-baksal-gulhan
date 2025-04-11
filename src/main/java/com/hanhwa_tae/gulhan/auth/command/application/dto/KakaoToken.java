package com.hanhwa_tae.gulhan.auth.command.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoToken {
    private String accessToken;
    private String refreshToken;
}
