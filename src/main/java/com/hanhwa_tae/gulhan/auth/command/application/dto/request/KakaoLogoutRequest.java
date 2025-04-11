package com.hanhwa_tae.gulhan.auth.command.application.dto.request;

import lombok.Data;

@Data
public class KakaoLogoutRequest {
    private String userId;
    private String kakaoAccessToken;
    private String kakaoRefreshToken;
}
