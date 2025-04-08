package com.hanhwa_tae.gulhan.auth.command.application.dto;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash(value = "KAKAO:TOKEN", timeToLive = 10000)
public class KakaoRefreshToken {

    @Id
    private String userId;

    private String refreshToken;

    private long expiresIn;


}
