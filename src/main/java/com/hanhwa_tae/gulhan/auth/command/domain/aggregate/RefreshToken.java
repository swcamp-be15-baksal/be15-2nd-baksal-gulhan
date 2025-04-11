package com.hanhwa_tae.gulhan.auth.command.domain.aggregate;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/* @Data:
 *  @Getter, @Setter, @ToString, @EqualsAndHashcode
 *  @RequiredArgsConstructor 를 한번에 하는 어노테이션
 */
@Data
@Builder
@RedisHash(timeToLive = 60480000)
public class RefreshToken {
    @Id
    private final String userId;

    private final Long userNo;
    private final String token;

}
