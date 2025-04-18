package com.hanhwa_tae.firstserver.review.query.mapper;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.firstserver.cart.query.dto.response.OrderHistoryResponse;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    boolean existsConfirmedOrder(@Param("userNo") Long userNo,
                                 @Param("targetId") int targetId,
                                 @Param("targetType") TargetType targetType);

    String selectOrderIdByUserNo(Long userNo);

    List<OrderHistoryResponse> selectOrderInfoByOrderId(String orderId);

    Order updateisConfirmedByOrderId(String orderId);


    int selectSumValue(Long userNo);

    Order findOrderInfo(String orderId);
}

