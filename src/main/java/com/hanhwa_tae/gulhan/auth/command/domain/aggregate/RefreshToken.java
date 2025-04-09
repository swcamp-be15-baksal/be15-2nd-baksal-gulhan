package com.hanhwa_tae.gulhan.auth.command.domain.aggregate;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/* @Data:
 *  @Getter, @Setter, @ToString, @EqualsAndHashcode
 *  @RequiredArgsConstructor 를 한번에 하는 어노테이션
 */
@Data
@Builder
@RedisHash(timeToLive = 60480000)
public class RefreshToken {
    @Id
    private final String token;

    @Indexed
    private final String userId;

}
