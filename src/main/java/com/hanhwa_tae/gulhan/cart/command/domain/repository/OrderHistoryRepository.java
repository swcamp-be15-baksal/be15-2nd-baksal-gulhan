package com.hanhwa_tae.gulhan.cart.command.domain.repository;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.OrderHistory;

public interface OrderHistoryRepository {
    OrderHistory save(OrderHistory orderHistory);
}
