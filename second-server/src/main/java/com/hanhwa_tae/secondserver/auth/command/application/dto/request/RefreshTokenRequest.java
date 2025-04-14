package com.hanhwa_tae.secondserver.auth.command.application.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RefreshTokenRequest {
    private final String refreshToken;
}
