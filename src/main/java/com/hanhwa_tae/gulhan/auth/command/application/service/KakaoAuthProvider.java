package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoTokenResponse;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.KakaoUserResponse;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.KakaoRefreshToken;
import com.hanhwa_tae.gulhan.auth.command.domain.repository.AuthRepository;
import com.hanhwa_tae.gulhan.auth.command.infrastructure.repository.RedisKakaoAuthRepository;
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
    private final RedisKakaoAuthRepository redisKakaoAuthRepository;

    @Value("${KAKAO_CLIENT_ID}")
    private String clientId;

    @Value("${KAKAO_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${KAKAO_REDIRECT_URI}")
    private String redirectUri;

    // 엑세스 & 리프레시 토큰 정보 받아오기
    public KakaoTokenResponse getAccessToken(String code) {

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
            ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                            "https://kauth.kakao.com/oauth/token",
                            HttpMethod.POST,
                            request,
                            KakaoTokenResponse.class
                    );

            return response.getBody();

        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
            System.out.println("토큰 정보를 받을 수 없습니다 : " + e.getStatusCode());
            System.out.println("Response body: " + e.getResponseBodyAsString());
            throw e;
        }


    }

    // 엑세스 토큰으로 유저 정보 받아 오기
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

    public KakaoTokenResponse getValidToken(String userId) {
        KakaoRefreshToken storedToken = redisKakaoAuthRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("저장된 리프레시 토큰이 없습니다."));

        Long now = System.currentTimeMillis();
        Long createdAt = storedToken.getCreatedAt();
        Long expiresIn = storedToken.getExpiresIn() * 1000;

        if (createdAt + expiresIn < now) {
            throw new IllegalArgumentException("리프레시 토큰이 만료되었습니다. 다시 로그인해주세요.");
        }

        return requestNewToken(userId, storedToken.getToken());
    }

     // refresh token으로 access token 재발급 + redis에 저장된 토큰 갱신
    private KakaoTokenResponse requestNewToken(String userId, String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", clientId);
        params.add("refresh_token", refreshToken);
        params.add("client_secret", clientSecret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<KakaoTokenResponse> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                KakaoTokenResponse.class
        );

        KakaoTokenResponse newToken = response.getBody();

        // refresh token이 새로 발급된 경우
        if (newToken.getRefreshToken() != null) {
            KakaoRefreshToken updatedToken = KakaoRefreshToken.builder()
                    .userId(userId)
                    .token(newToken.getRefreshToken())
                    .expiresIn(newToken.getRefreshTokenExpiresIn())
                    .createdAt(System.currentTimeMillis())
                    .build();

            redisKakaoAuthRepository.save(updatedToken);
        }

        return newToken;
    }



}

