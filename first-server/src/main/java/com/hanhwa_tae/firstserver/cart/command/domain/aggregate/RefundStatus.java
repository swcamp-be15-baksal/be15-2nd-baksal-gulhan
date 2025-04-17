package com.hanhwa_tae.firstserver.cart.command.domain.aggregate;

import lombok.Getter;

@Getter
public enum RefundStatus {
    REQUESTED("신청"),
    RECEIVED("접수"),
    COMPLETED("완료"),
    REJECTED("반려");

    private final String refundStatus;

    RefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
}
