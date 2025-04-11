package com.hanhwa_tae.gulhan.cart.query.dto.response;


import com.hanhwa_tae.gulhan.common.domain.TargetType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {
    private int cartId;
    private int quantity;
    private TargetType targetType;
}
