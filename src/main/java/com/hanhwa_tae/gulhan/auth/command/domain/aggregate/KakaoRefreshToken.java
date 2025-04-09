package com.hanhwa_tae.gulhan.auth.command.domain.aggregate;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("KAKAO:TOKEN")
public class KakaoRefreshToken {

    @Id
    private String userId;

    private String refreshToken;

    private long expiresIn;

    private long createdAt;


}
