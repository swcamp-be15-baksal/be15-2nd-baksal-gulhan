package com.hanhwa_tae.gulhan.cart.query.dto.request;

import lombok.Getter;

@Getter
public class CartSearchRequest {
    private Integer page = 1;
    private Integer size = 5;
}
