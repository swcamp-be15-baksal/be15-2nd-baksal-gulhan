package com.hanhwa_tae.gulhan.cart.query.dto.response;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CartDetailResponse {
    private final List<CartDetailResponse> carts;
    private final Pagination pagination;
}
