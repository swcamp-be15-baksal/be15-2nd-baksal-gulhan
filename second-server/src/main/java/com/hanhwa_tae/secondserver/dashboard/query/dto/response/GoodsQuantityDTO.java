package com.hanhwa_tae.secondserver.dashboard.query.dto.response;

import com.hanhwa_tae.secondserver.common.domain.TargetType;
import lombok.Getter;

@Getter
public class GoodsQuantityDTO {

    private TargetType OrderHistoryType;

    private int targetId;

    private String title;

    private int totalQuantity;

    private int totalPrice;
}
