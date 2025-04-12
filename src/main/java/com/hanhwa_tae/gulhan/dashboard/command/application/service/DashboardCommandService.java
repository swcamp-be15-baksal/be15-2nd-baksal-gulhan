package com.hanhwa_tae.gulhan.dashboard.command.application.service;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.gulhan.dashboard.command.application.dto.request.OrderUpdateRequest;
import com.hanhwa_tae.gulhan.dashboard.command.domain.repository.JpaOrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardCommandService {

    private final JpaOrderRepository jpaOrderRepository;

    /* 운송장 번호, 주문코드, 받는사람 정보 수정 */
    @Transactional
    public void updateOrder(Long orderId, OrderUpdateRequest orderUpdateRequest) {

        Order order =  jpaOrderRepository.findById(orderId)
                .orElseThrow( () -> new RuntimeException("주문 정보 없음"));

        order.update(
                  orderUpdateRequest.getAddress()
                , orderUpdateRequest.getReceiver()
                , orderUpdateRequest.getReceiverPhone()
                , orderUpdateRequest.getShippingNo()
        );
    }
}
