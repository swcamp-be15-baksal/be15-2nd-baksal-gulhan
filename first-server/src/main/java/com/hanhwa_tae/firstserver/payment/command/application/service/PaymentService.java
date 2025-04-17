package com.hanhwa_tae.firstserver.payment.command.application.service;


import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.payment.command.domain.aggregate.Redispayinfo;
import com.hanhwa_tae.firstserver.payment.command.infrastructure.repository.RedisPayInfoRepository;
import com.hanhwa_tae.firstserver.review.query.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final RedisPayInfoRepository redisPayInfoRepository;
    private final OrderMapper orderMapper;

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

    public String updateConfirm(Long userNo, String order_id) {
        if(!orderMapper.selectOrderIdByUserNo(userNo).equalsIgnoreCase(order_id)){
            throw new BusinessException(ErrorCode.NOT_MATCHING_ORDER);
        }

        orderMapper.updateisConfirmedByOrderId(order_id);

        // 등급 변동
      /*  for(){

        }*/

        return "ok";
    }
}
