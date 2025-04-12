package com.hanhwa_tae.gulhan.user.command.domain.aggregate;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "register",timeToLive = 600000)
public class RedisUser implements Serializable {
    @Id
    private String uuid;
    private String userData;
}