package com.hanhwa_tae.gulhan.auth.command.application.controller;

import com.hanhwa_tae.gulhan.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.gulhan.auth.command.application.service.AuthCommandService;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthCommandController {
    private final AuthCommandService authCommandService;

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody RefreshTokenRequest refreshToken
    ) {
        authCommandService.logout(userDetail, refreshToken);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
