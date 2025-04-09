package com.hanhwa_tae.gulhan.dashboard.query.dto.response;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import lombok.Getter;

@Getter
public class PackageQuantityDTO {

    private TargetType OrderHistoryType;

    private int targetId;

    private int totalQuantity;

    private int totalPrice;

}
