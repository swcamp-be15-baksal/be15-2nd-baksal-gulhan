package com.hanhwa_tae.gulhan.payment.command.application.service;


import com.hanhwa_tae.gulhan.payment.command.domain.aggregate.Redispayinfo;
import com.hanhwa_tae.gulhan.payment.command.infrastructure.repository.RedisPayInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final RedisPayInfoRepository redisPayInfoRepository;

    public void savePayment(int value, String orderId) {
        Redispayinfo redispayinfo = new Redispayinfo(orderId, value);
        redisPayInfoRepository.save(redispayinfo);

    }
    public boolean validatePayment(String orderId, int value){
        Optional<Redispayinfo> stored_redis_info = redisPayInfoRepository.findById(orderId);
        return stored_redis_info.
                filter(redispayinfo -> redispayinfo.getAmount() == value).
                isPresent();

    }

    public void saveOrder(Long value, )
}
