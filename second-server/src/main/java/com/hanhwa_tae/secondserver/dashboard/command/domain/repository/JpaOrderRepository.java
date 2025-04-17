package com.hanhwa_tae.secondserver.dashboard.command.domain.repository;

import com.hanhwa_tae.secondserver.cart.command.domain.aggregate.Order;
import org.springframework.data.jpa.repository.JpaRepository;
/* cart 쪽으로 옮겨야함 */
public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
