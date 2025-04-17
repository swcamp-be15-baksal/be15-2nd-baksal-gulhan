package com.hanhwa_tae.firstserver.payment.query.service;

import com.hanhwa_tae.firstserver.cart.query.dto.response.OrderHistoryResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderDetailResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderHistoryDetailResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderResponse;
import com.hanhwa_tae.firstserver.payment.query.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class SearchPaymentService {
    private final PaymentMapper paymentMapper;
    public OrderHistoryDetailResponse findHistory(String orderId) {
        List<OrderHistoryResponse> orderIdList = paymentMapper.findOrderByOrderId(orderId);

        return OrderHistoryDetailResponse.builder().
                orderHistories(orderIdList).
                build();
    }

    public OrderDetailResponse findOrder(Long userNo) {
        List<OrderResponse> orders = paymentMapper.findOrderInfoByUserNo(userNo);

        return OrderDetailResponse.builder().
                orders(orders).build();
    }
}
