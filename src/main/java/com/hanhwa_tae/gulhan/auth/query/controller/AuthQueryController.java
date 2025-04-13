package com.hanhwa_tae.gulhan.auth.query.controller;

import com.hanhwa_tae.gulhan.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.gulhan.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.gulhan.auth.query.dto.response.AccessTokenResponse;
import com.hanhwa_tae.gulhan.auth.query.service.AuthQueryService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthQueryController {
    private final AuthQueryService authQueryService;

    @Operation(summary = "로그인", description = "회원은 아이디 비밀번호 입력을 통해 로그인할 수 있다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(
            @RequestBody LoginRequest request){
        TokenResponse tokenResponse = authQueryService.login(request);

        return ResponseEntity.ok(ApiResponse.success(tokenResponse));
    }

    @Operation(summary = "엑세스 토큰 재발행", description = "회원은 엑세스 토큰 재발행 요청을 할 수 있다.")
    @PostMapping("/token/reissue")
    public ResponseEntity<ApiResponse<AccessTokenResponse>> reissue(
            @RequestBody RefreshTokenRequest request
            ){

        AccessTokenResponse tokenResponse = authQueryService.reissue(request);

        return ResponseEntity.ok(ApiResponse.success(tokenResponse));
    }
}
