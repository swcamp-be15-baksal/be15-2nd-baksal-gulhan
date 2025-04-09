package com.hanhwa_tae.gulhan.cart.command.application.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateCartResponse {
    private int cartId;
    private int cast;
}
