package com.hanhwa_tae.firstserver.payment.command.application.service;


import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.OrderHistory;
import com.hanhwa_tae.firstserver.cart.query.dto.response.OrderHistoryResponse;
import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.payment.command.domain.aggregate.Redispayinfo;
import com.hanhwa_tae.firstserver.payment.command.infrastructure.repository.RedisPayInfoRepository;
import com.hanhwa_tae.firstserver.review.query.mapper.OrderMapper;
import com.hanhwa_tae.firstserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final RedisPayInfoRepository redisPayInfoRepository;
    private final OrderMapper orderMapper;
    private final UserMapper userMapper;

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

        Order order =orderMapper.updateisConfirmedByOrderId(order_id);
        int result_val = orderMapper.selectSumValue(userNo);
        // 등급 변동
/*평민, 중인 20만원, 양반 50만원, 왕 100만원 */
        if(200000<= result_val && result_val<500000){
            userMapper.updateRank(userNo,3);
        }else if(500000<= result_val && result_val <1000000){
            userMapper.updateRank(userNo,4);
        }else if(1000000 <= result_val){
            userMapper.updateRank(userNo,5);

        }
        return "ok";
    }
}
