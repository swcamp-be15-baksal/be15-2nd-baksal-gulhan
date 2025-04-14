package com.hanhwa_tae.firstserver.review.query.dto.request;

import com.hanhwa_tae.firstserver.common.domain.TargetType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReviewInsertRequest {
    private int targetId;

    private TargetType targetType;

    private String detail;

    private BigDecimal rating;

    private Long userNo;
}
