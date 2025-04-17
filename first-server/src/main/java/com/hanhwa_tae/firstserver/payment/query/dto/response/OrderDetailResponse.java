package com.hanhwa_tae.firstserver.payment.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class OrderDetailResponse {

    private final List<OrderResponse> orders;
}
