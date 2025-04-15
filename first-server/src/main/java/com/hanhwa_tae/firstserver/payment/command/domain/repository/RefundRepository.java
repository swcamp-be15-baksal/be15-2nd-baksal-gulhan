package com.hanhwa_tae.firstserver.payment.command.domain.repository;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Refund;

public interface RefundRepository {
    Refund save(Refund refund);
}
