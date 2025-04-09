package com.hanhwa_tae.gulhan.auth.command.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class KakaoTokenResponse {   // 카카오가 준 토큰 응답

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("refresh_token_expires_in")
    private long refreshTokenExpiresIn;

    private String scope;
}

