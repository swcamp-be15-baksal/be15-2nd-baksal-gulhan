package com.hanhwa_tae.gulhan.auth.command.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KakaoTokenResponse {   // 카카오가 준 토큰 응답
    private String tokenType;
    private String accessToken;
    private long expiresIn;
    private String refreshToken;
    private long refreshTokenExpiresIn;
    private String scope;
}

