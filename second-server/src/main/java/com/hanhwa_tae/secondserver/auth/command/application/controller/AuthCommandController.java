package com.hanhwa_tae.secondserver.auth.command.application.controller;

import com.hanhwa_tae.secondserver.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.secondserver.auth.command.application.service.AuthCommandService;
import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthCommandController {
    private final AuthCommandService authCommandService;

    @Operation(summary = "로그아웃", description = "회원은 로그아웃 할 수 있다.")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody RefreshTokenRequest refreshToken
    ) {
        authCommandService.logout(userDetail, refreshToken);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
