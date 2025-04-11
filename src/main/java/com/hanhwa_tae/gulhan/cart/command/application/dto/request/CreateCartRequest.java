package com.hanhwa_tae.gulhan.cart.command.application.dto.request;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import lombok.*;

@Getter
public class CreateCartRequest {
    private final int quantity;
    private final TargetType targetType;
    private final int targetId;


    @Builder
    public CreateCartRequest(int quantity, TargetType targetType, int targetId){
        this.quantity = quantity;
        this.targetType = targetType;
        this.targetId = targetId;
    }


}
