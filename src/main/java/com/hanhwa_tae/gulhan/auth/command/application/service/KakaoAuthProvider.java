package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoUserResponse;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.common.exception.KakaoAuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoAuthProvider {

    private final RestTemplate restTemplate;

    @Value("${KAKAO_CLIENT_ID}")
    private String clientId;

    @Value("${KAKAO_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${KAKAO_REDIRECT_URI}")
    private String redirectUri;

    private static final String KAKAO_TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private static final String KAKAO_LOGOUT_URL = "https://kapi.kakao.com/v1/user/logout";
    private static final String KAKAO_UNLINK_URL = "https://kapi.kakao.com/v1/user/logout";

    // 카카오 토큰 받기
    public KakaoTokenResponse getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);

        return requestToken(params);
    }

    // 카카오 토큰 갱신
    public KakaoTokenResponse requestNewToken(String refreshToken) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", clientId);
        params.add("refresh_token", refreshToken);
        params.add("client_secret", clientSecret);

        return requestToken(params);
    }

    // 카카오에게 토큰 갱신 요청
    private KakaoTokenResponse requestToken(MultiValueMap<String, String> params) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, defaultFormUrlHeaders());

        try {
            ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                    KAKAO_TOKEN_URL,
                    HttpMethod.POST,
                    request,
                    KakaoTokenResponse.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            String responseBody = e.getResponseBodyAsString();
            String kakaoErrorCode = extractKakaoErrorCode(responseBody);
            ErrorCode mappedError = mapKakaoErrorCode(kakaoErrorCode);

            log.warn("\uD83D\uDEA8 카카오 토큰 요청 실패: code={}, message={} ", kakaoErrorCode, responseBody);
            throw new KakaoAuthException(mappedError);
        }
    }

    // 카카오 토큰으로 유저 정보 받기
    public KakaoUserResponse getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<KakaoUserResponse> response = restTemplate.exchange(
                    KAKAO_USER_INFO_URL,
                    HttpMethod.POST,
                    request,
                    KakaoUserResponse.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("카카오 유저 정보 조회 실패", e);
            throw new KakaoAuthException(ErrorCode.KAKAO_TOKEN_INVALID);
        }
    }

    // 카카오 로그인 계정 로그아웃
    public void logout(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_LOGOUT_URL,
                    HttpMethod.POST,
                    request,
                    String.class
            );
            log.info("카카오 로그아웃 성공: {}", response.getBody());
        } catch (HttpClientErrorException e) {
            log.error("카카오 로그아웃 실패", e);
            throw new KakaoAuthException(ErrorCode.KAKAO_AUTH_FAILED);        }
    }

    // 카카오 계정과 연결 끊기 (탈퇴 기능)
    public void unlink(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    KAKAO_UNLINK_URL,
                    HttpMethod.POST,
                    request,
                    String.class
            );
            log.info("카카오 연결 해제 성공: {}", response.getBody());
        } catch (HttpClientErrorException e) {
            log.error("카카오 연결 해제 실패", e);
            throw new KakaoAuthException(ErrorCode.KAKAO_AUTH_FAILED);
        }
    }


    private HttpHeaders defaultFormUrlHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private String extractKakaoErrorCode(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(responseBody);
            return root.path("error_code").asText();
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }

    private ErrorCode mapKakaoErrorCode(String kakaoErrorCode) {
        return switch (kakaoErrorCode) {
            case "KOE320" -> ErrorCode.KAKAO_TOKEN_INVALID;
            case "KOE303" -> ErrorCode.KAKAO_TOKEN_EXPIRED;
            default -> ErrorCode.KAKAO_AUTH_FAILED;
        };
    }



}