package com.hanhwa_tae.gulhan.payment.command.application.dto.request;

import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

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
