package com.hanhwa_tae.gulhan.payment.command.application.dto.request;

import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class CreateOrderRequest {
    private final String orderId;
    private final LocalDateTime orderAt;
    private final int totalPrice;
    private final int totalPoint;
    private final int totalAmout;
    private final String shoppingNo;
    private final String orderCode;

    @Builder
    public CreateOrderRequest(String orderId, LocalDateTime orderAt,
                             int totalPrice, int totalPoint,
                             int totalAmount, String shoppingNo,
                             String orderCode){
        this.orderId = orderId;
        this.orderAt = orderAt;
        this.totalPrice = totalPrice;
        this.totalPoint = totalPoint;
        this.totalAmout = totalAmount;
        this.shoppingNo = shoppingNo;
        this.orderCode = orderCode;
    }
}
