package com.hanhwa_tae.firstserver.payment.query.mapper;
import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.firstserver.cart.query.dto.response.OrderHistoryResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderHistoriesResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentMapper {
    Optional<Order> findOrderByUserNo(Long userNo);

    List<OrderHistoryResponse> findOrderByOrderId(String orderId);

    List<OrderResponse> findOrderInfoByUserNo(Long userNo);

    List<OrderHistoriesResponse> findOrderHistoryByUserNo(Long userNo);
}
