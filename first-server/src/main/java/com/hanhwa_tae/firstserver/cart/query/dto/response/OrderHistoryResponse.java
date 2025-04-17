package com.hanhwa_tae.firstserver.cart.query.dto.response;


import com.hanhwa_tae.firstserver.common.domain.TargetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderHistoryResponse {
    private final int orderHistoryId;
    private final TargetType orderHistoryType;
    private final int targetId;
    private final int quantity;
}

