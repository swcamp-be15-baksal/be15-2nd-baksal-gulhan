package com.hanhwa_tae.secondserver.auth.query.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class AccessTokenResponse {
    private final String accessToken;
}
