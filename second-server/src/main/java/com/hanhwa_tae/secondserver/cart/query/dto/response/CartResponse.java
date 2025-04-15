package com.hanhwa_tae.secondserver.cart.query.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartResponse {
    private int cartId;
    private int quantity;
    private String targetType;
}
