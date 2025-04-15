package com.hanhwa_tae.firstserver.payment.query.dto.response;

import com.hanhwa_tae.firstserver.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.firstserver.cart.query.dto.response.OrderHistoryResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@Builder
public class OrderHistoryDetailResponse {
    private final List<OrderHistoryResponse> orderHistories ;

}
