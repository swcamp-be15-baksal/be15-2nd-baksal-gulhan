package com.hanhwa_tae.firstserver.payment.command.infrastructure.repository;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Refund;
import com.hanhwa_tae.firstserver.payment.command.domain.repository.RefundRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRefundRepository extends RefundRepository, JpaRepository<Refund, String> {
}
