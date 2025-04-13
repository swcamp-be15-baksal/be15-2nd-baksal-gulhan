package com.hanhwa_tae.gulhan.payment.query.mapper;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface OrderMapper {
    Optional<Order> findOrderByUserNo(Long userNo);
}
