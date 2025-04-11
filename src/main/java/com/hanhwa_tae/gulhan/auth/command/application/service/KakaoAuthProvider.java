package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    // 엑세스 토큰 정보 받아오기
    public KakaoTokenResponse getAccessToken(String code) {
        System.out.println("clientId: " + clientId);
        System.out.println("clientSecret: " + clientSecret);
        System.out.println("redirectUri: " + redirectUri);
        System.out.println("code: " + code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<KakaoTokenResponse> response =
                    restTemplate.exchange(
                            "https://kauth.kakao.com/oauth/token",
                            HttpMethod.POST,
                            request,
                            KakaoTokenResponse.class
                    );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Kakao token error: " + e.getStatusCode());
            System.out.println("Response body: " + e.getResponseBodyAsString());
            throw e;
        }


    }

    // 토큰으로 유저 정보 받아 오기
    public KakaoUserResponse getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);  // Authorization: Bearer ${ACCESS_TOKEN}

        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<KakaoUserResponse> response = restTemplate.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.POST,
                    request,
                    KakaoUserResponse.class
            );

            return response.getBody();

        } catch (HttpClientErrorException e) {
            System.out.println("카카오 유저 정보 조회 실패");
            System.out.println("Status: " + e.getStatusCode());
            System.out.println("Body: " + e.getResponseBodyAsString());
            throw e;
        }
    }




}

