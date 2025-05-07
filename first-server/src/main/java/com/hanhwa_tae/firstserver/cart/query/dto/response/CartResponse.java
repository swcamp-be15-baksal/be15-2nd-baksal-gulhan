package com.hanhwa_tae.firstserver.cart.query.dto.response;


import com.hanhwa_tae.firstserver.common.domain.TargetType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {
    private int cartId;
    private int quantity;
    private TargetType targetType;
    private int targetId;
    private String title;
    private int price;
}
