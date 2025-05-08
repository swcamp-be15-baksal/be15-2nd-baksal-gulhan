package com.hanhwa_tae.firstserver.payment.query.dto.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class OrderHistoriesDetailResponse {
    private List<OrderHistoriesResponse> orderHistories;
}
