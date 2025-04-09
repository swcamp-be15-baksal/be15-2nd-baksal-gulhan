package com.hanhwa_tae.gulhan.auth.command.application.dto.response;

import com.hanhwa_tae.gulhan.auth.command.application.dto.request.KakaoTokenRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoLoginResponse {
    private String userId;
    private String username;
    private KakaoTokenRequest token;
}
