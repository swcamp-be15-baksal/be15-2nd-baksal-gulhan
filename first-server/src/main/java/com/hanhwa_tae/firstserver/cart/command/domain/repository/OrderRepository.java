package com.hanhwa_tae.firstserver.cart.command.domain.repository;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Order;

public interface OrderRepository {
    Order save(Order order);
}
