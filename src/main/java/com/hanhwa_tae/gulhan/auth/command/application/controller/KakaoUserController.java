package com.hanhwa_tae.gulhan.auth.command.application.controller;

import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoLoginResponse;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.gulhan.auth.command.application.service.KakaoAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/oauth/kakao")
public class KakaoUserController {

    private final KakaoAuthService kakaoAuthService;

    @Value("${KAKAO_CLIENT_ID}")
    private String clientId;

    @Value("${KAKAO_REDIRECT_URI}")
    private String redirectUri;

    @GetMapping("/callback")
    public ResponseEntity<?> kakaoCallback(@RequestParam("code") String code) {
        KakaoLoginResponse response = kakaoAuthService.getKakaoToken(code);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/login")
    public RedirectView redirectToKakaoLogin() {
        String kakaoAuthUrl = UriComponentsBuilder.fromHttpUrl("https://kauth.kakao.com/oauth/authorize")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("response_type", "code")
                .toUriString();

        return new RedirectView(kakaoAuthUrl);
    }

    @GetMapping("/refresh")
    public ResponseEntity<KakaoTokenResponse> refreshToken(@RequestParam String userId) {
        KakaoTokenResponse response = kakaoAuthService.getRefreshToken(userId);
        return ResponseEntity.ok(response);
    }



}
