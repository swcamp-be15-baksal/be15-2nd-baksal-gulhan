package com.hanhwa_tae.firstserver.cart.command.infrastructure.repository;


import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.firstserver.cart.command.domain.repository.OrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderedRepository extends OrderRepository, JpaRepository<Order, String> {
}
