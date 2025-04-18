package com.hanhwa_tae.firstserver.payment.command.application.service;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Refund;
import com.hanhwa_tae.firstserver.payment.command.application.dto.request.CreateRefundRequest;
import com.hanhwa_tae.firstserver.payment.command.domain.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class RefundCommandService {
    private final RefundRepository refundRepository;

    public String createRefund(CreateRefundRequest createRefundRequest) {
        refundRepository.save(
                Refund.builder().
                        orderId(createRefundRequest.getOrderId()).
                        refundReason(createRefundRequest.getRefundReason()).
                        refundStatus(createRefundRequest.getRefundStatus()).
                        refundedAt(new Timestamp(System.currentTimeMillis()))
                        .build()

        );

        return "ok";
    }

}
