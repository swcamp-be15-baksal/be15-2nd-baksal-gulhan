package com.hanhwa_tae.secondserver.auth.command.application.controller;

import com.hanhwa_tae.secondserver.auth.command.application.dto.request.KakaoAdditionalInfoRequest;
import com.hanhwa_tae.secondserver.auth.command.application.dto.request.KakaoLogoutRequest;
import com.hanhwa_tae.secondserver.auth.command.application.dto.request.KakaoRefreshRequest;
import com.hanhwa_tae.secondserver.auth.command.application.dto.request.KakaoWithdrawRequest;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoLoginResponse;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.secondserver.auth.command.application.service.KakaoAuthService;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@Tag(name = "소셜 회원")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/kakao")
public class KakaoUserController {

    private final KakaoAuthService kakaoAuthService;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    // 로그인 페이지에서 로그인 후 /callback 으로 리다이렉트
    @Operation(summary = "카카오 소셜 로그인", description = "회원은 카카오 소셜 로그인을 할 수 있다.")
    @GetMapping("/login")
    public RedirectView redirectToKakaoLogin() {
        String kakaoAuthUrl =
                UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .toUriString();

        return new RedirectView(kakaoAuthUrl);
    }

    // 카카오 로그인 콜백 처리 (인가 코드로 로그인 요청)
    @Operation(summary = "카카오 엑세스 토큰 요청", description = "회원은 카카오 로그인에 성공 시 인가 코드로 토큰을 발급 받을 수 있다.")
    @GetMapping("/callback")
    public RedirectView kakaoCallback(@RequestParam("code") String code) {
        KakaoLoginResponse response = kakaoAuthService.login(code);

        String accessToken = response.getToken().getAccessToken();
        String refreshToken = response.getToken().getRefreshToken();
        boolean needsAdditionalInfo = response.isNeedsAdditionalInfo();

        String redirectUrl = UriComponentsBuilder
                .fromUriString("http://localhost:5173/kakao/callback")
                .queryParam("accessToken", accessToken)
                .queryParam("refreshToken", refreshToken)
                .queryParam("needsAdditionalInfo", needsAdditionalInfo)
                .queryParam("userId", response.getUserId())
                .queryParam("username", response.getUsername())
                .build()
                .toUriString();

        return new RedirectView(redirectUrl);
    }

    // 추가 정보 입력
    @Operation(summary = "추가 정보 입력", description = "소셜 회원가입한 유저는 추가 정보 입력을 요구 받는다. ")
    @PostMapping("/additional-info")
    public ResponseEntity<ApiResponse<String>> addKakaoUserInfo(
            @RequestBody @Valid KakaoAdditionalInfoRequest request
    ) {
        log.info("추가 회원 정보 입력 호출");
        kakaoAuthService.updateKakaoUserInfo(request);
        return ResponseEntity.ok(ApiResponse.success("추가 정보 등록이 완료되었습니다."));
    }

    // 카카오 엑세스 토큰 갱신 요청
    @Operation(summary = "카카오 엑세스 토큰 갱신", description = "카카오 엑세스 토큰 만료시 재발급 요청을 진행한다. ")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<KakaoTokenResponse>> refresh(@RequestBody @Valid KakaoRefreshRequest request) {
        KakaoTokenResponse response = kakaoAuthService.refreshTokenByKakao(request.getRefreshToken());
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 카카오 계정 로그아웃
    @Operation(summary = "카카오 로그아웃", description = "카카오 로그인 유저에 대한 로그아웃을 진행한다. ")
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(@RequestBody @Valid KakaoLogoutRequest request) {
        log.info("카카오 로그아웃 요청: userId={}", request.getUserId());
        kakaoAuthService.logout(
                request.getUserId(),
                request.getAccessToken(),
                request.getRefreshToken()
        );
        return ResponseEntity.ok(ApiResponse.success("카카오 계정 로그아웃에 성공했습니다."));
    }

    // 카카오 계정 연결끊기 (탈퇴)
    @Operation(summary = "카카오 회원 탈퇴", description = "카카오 로그인 유저에 대한 회원 탈퇴를 진행한다.")
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<String>> withdraw(@RequestBody @Valid KakaoWithdrawRequest request) {
        kakaoAuthService.withdraw(request.getUserId(), request.getAccessToken());
        return ResponseEntity.ok(ApiResponse.success("회원 탈퇴 및 카카오 계정 연결 해제가 완료되었습니다."));
    }


}
