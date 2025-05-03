package com.hanhwa_tae.secondserver.auth.query.controller;

import com.hanhwa_tae.secondserver.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.secondserver.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.secondserver.auth.query.dto.response.AccessTokenResponse;
import com.hanhwa_tae.secondserver.auth.query.service.AuthQueryService;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Tag(name = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthQueryController {
    private final AuthQueryService authQueryService;

    @Operation(summary = "로그인", description = "회원은 아이디 비밀번호 입력을 통해 로그인할 수 있다.")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(
            @RequestBody LoginRequest request){
        TokenResponse tokenResponse = authQueryService.login(request);

        return buildTokenResponse(tokenResponse);
    }

    @Operation(summary = "엑세스 토큰 재발행", description = "회원은 엑세스 토큰 재발행 요청을 할 수 있다.")
    @PostMapping("/token/reissue")
    public ResponseEntity<ApiResponse<TokenResponse>> reissue(
            @CookieValue(name = "refreshToken", required = false) String refreshToken // HttpOnly 쿠키에서 읽어옴
            ){

        TokenResponse tokenResponse = authQueryService.reissue(refreshToken);

        return buildTokenResponse(tokenResponse);
    }
    /* accessToken -> body
    * refreshToken -> cookie*/
    private ResponseEntity<ApiResponse<TokenResponse>> buildTokenResponse(TokenResponse tokenResponse){
        ResponseCookie cookie = createRefreshTokenCookie(tokenResponse.getRefreshToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(ApiResponse.success(tokenResponse));
    }

    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)                     // HttpOnly 속성 설정 (JavaScript 에서 접근 불가)
                // .secure(true)                    // HTTPS 환경일 때만 전송 (운영 환경에서 활성화 권장)
                .path("/")                          // 쿠키 범위 : 전체 경로
                .maxAge(Duration.ofDays(7))         // 쿠키 만료 기간 : 7일
                .sameSite("Strict")                 // CSRF 공격 방어를 위한 SameSite 설정
                .build();
    }
}
