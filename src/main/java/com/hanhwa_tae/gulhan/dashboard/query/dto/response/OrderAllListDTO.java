package com.hanhwa_tae.gulhan.dashboard.query.dto.response;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Order;
import lombok.Getter;

@Getter
public class OrderAllListDTO {
    Order order;
    private String userName;
}
