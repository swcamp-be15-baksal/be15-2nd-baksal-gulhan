package com.hanhwa_tae.gulhan.auth.command.application.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private final String accessToken;
    private final String refreshToken;
}
