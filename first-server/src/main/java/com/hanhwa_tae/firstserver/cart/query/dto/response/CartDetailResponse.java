package com.hanhwa_tae.firstserver.cart.query.dto.response;

import com.hanhwa_tae.firstserver.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CartDetailResponse {
    private final List<CartResponse> carts;
    private final Pagination pagination;
}
