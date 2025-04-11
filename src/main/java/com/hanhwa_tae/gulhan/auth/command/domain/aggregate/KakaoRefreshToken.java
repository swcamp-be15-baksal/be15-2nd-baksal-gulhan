package com.hanhwa_tae.gulhan.auth.command.domain.aggregate;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Data
@Builder
@RedisHash("KAKAO:TOKEN")
public class KakaoRefreshToken {

    @Id
    private String token;

    @Indexed
    private String userId;

    @TimeToLive
    private Long expiresIn;
}

