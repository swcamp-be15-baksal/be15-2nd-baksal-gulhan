package com.hanhwa_tae.firstserver.cart.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartSearchRequest {
    private Integer page = 1;
    private Integer size = 5;

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit(){
        return size;
    }
}
