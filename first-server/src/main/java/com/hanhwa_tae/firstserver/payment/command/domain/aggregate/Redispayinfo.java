package com.hanhwa_tae.firstserver.payment.command.domain.aggregate;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(timeToLive = 600000)
public class Redispayinfo implements Serializable {
    @Id
    private String orderId;
    private Integer amount;
}