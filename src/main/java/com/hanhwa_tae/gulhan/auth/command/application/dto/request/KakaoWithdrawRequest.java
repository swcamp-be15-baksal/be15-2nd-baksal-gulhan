package com.hanhwa_tae.gulhan.auth.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoWithdrawRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String accessToken;
}
