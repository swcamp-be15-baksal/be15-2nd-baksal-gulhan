package com.hanhwa_tae.gulhan.dashboard.query.dto.response;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DailyRevenueDTO {

    private TargetType orderHistoryType;

    private LocalDateTime orderedAt;

    private int totalQuantity;

    private int totalPrice;
}
