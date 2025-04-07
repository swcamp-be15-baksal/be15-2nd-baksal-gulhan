package com.hanhwa_tae.gulhan.auth.query.controller;

import com.hanhwa_tae.gulhan.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.gulhan.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.gulhan.auth.query.service.AuthQueryService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthQueryController {
    private final AuthQueryService authQueryService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(LoginRequest request){
        TokenResponse tokenResponse = authQueryService.login(request);

        return ResponseEntity.ok(ApiResponse.success(tokenResponse));
    }
}
