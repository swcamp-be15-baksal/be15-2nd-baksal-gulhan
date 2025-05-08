package com.hanhwa_tae.firstserver.payment.query.dto.response;


import lombok.Builder;

import java.util.List;

@Builder
public class OrderHistoriesDetailResponse {
    private List<OrderHistoriesResponse> orderHistories;
}
