package com.hanhwa_tae.gulhan.cart.command.infrastructure.repository;


import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.OrderHistory;
import com.hanhwa_tae.gulhan.cart.command.domain.repository.OrderHistoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderHistoryRepository extends OrderHistoryRepository, JpaRepository<OrderHistory, Integer> {
}
