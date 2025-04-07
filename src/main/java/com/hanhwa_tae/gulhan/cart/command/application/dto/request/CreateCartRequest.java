package com.hanhwa_tae.gulhan.cart.command.application.dto.request;

import lombok.*;

@Getter
public class CreateCartRequest {
    private final int quantity;
    private final String targetType;
    private final int targetId;


    @Builder
    public CreateCartRequest(int quantity, String targetType, int targetId){
        this.quantity = quantity;
        this.targetType = targetType;
        this.targetId = targetId;
    }


}
