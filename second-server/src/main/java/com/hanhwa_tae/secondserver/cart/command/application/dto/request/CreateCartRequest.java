package com.hanhwa_tae.secondserver.cart.command.application.dto.request;

import com.hanhwa_tae.secondserver.common.domain.TargetType;
import lombok.Builder;
import lombok.Getter;

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
