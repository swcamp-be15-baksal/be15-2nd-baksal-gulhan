package com.hanhwa_tae.firstserver.payment.command.application.dto.request;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.RefundStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;


@Getter
@RequiredArgsConstructor
public class CreateRefundRequest {
    private final String orderId;
    private final String refundReason;
    private final RefundStatus refundStatus;
    private final Date refundedAt;
}
