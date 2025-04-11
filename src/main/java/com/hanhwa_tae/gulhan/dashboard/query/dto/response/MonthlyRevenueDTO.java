package com.hanhwa_tae.gulhan.dashboard.query.dto.response;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import lombok.Getter;

@Getter
public class MonthlyRevenueDTO {

    private TargetType orderHistoryType;

    private String orderedAt;

    private int totalQuantity;

    private int totalPrice;
}
