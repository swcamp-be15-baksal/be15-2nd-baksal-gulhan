package com.hanhwa_tae.firstserver.payment.query.dto.response;

import com.hanhwa_tae.firstserver.cart.query.dto.response.OrderHistoryResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class OrderHistoryDetailResponse {
    private final List<OrderHistoryResponse> orderHistories ;

}
