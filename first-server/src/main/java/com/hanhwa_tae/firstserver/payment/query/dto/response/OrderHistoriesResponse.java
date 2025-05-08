package com.hanhwa_tae.firstserver.payment.query.dto.response;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Confirm;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Getter
@NoArgsConstructor  // 기본 생성자 추가
@AllArgsConstructor // 선택적으로 모든 필드를 받는 생성자 추가 (Optional)
@Builder
public class OrderHistoriesResponse {
    private TargetType orderHistoryType;
    private int targetId;
    private int quantity;
    private String orderId;
    private Timestamp orderedAt;
    private Confirm isConfirmed;
    private int price;
}
