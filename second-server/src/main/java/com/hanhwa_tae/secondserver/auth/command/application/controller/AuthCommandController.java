package com.hanhwa_tae.secondserver.auth.command.application.controller;

import com.hanhwa_tae.secondserver.auth.command.application.service.AuthCommandService;
import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        System.out.println(refreshToken);
        if (refreshToken != null) {
            authCommandService.logout(userDetail, refreshToken);
        }
        ResponseCookie deleteCookie = createDeleteRefreshTokenCookie(); // 만료용 쿠키 생성
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(ApiResponse.success(null));
    }

    /* 쿠키 삭제용*/
    private ResponseCookie createDeleteRefreshTokenCookie() {
        return ResponseCookie.from("refreshToken", "")
                .httpOnly(true)     // HttpOnly 유지
                // .secure(true)    // HTTPS 환경에서만 사용 시 주석 해제
                .path("/")          // 동일 path 범위
                .maxAge(0)          // 즉시 만료
                .sameSite("Strict") // SameSite 유지
                .build();
    }
}
