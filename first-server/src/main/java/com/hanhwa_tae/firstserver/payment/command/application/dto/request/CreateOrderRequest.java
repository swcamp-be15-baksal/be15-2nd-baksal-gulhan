package com.hanhwa_tae.firstserver.payment.command.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateOrderRequest {
    private final String orderId;
    private final int totalPrice;
    private final String orderCode;

    @Builder
    public CreateOrderRequest(String orderId, int totalPrice,
                              String orderCode){
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderCode = orderCode;
    }
}
