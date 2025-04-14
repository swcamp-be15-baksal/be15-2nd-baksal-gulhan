package com.hanhwa_tae.secondserver.auth.command.application.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoLogoutRequest {
    private String userId;
    private String accessToken;
    private String refreshToken;
}
