package com.hanhwa_tae.secondserver.auth.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoRefreshRequest {
    @NotBlank
    private String refreshToken;
}
