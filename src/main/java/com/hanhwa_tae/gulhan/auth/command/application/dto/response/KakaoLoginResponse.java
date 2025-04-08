package com.hanhwa_tae.gulhan.auth.command.application.dto.response;

import com.hanhwa_tae.gulhan.auth.command.application.dto.request.KakaoTokenRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoLoginResponse {
    private String userId;
    private String username;
    private KakaoTokenRequest token;
}
