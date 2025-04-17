package com.hanhwa_tae.firstserver.payment.query.dto.response;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.RefundStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RefundResponse {
    private String orderId;
    private String refundReason;
    private RefundStatus refundStatus;
    private Timestamp refundedAt;
}
