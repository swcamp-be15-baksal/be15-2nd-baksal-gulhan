package com.hanhwa_tae.secondserver.user.query.dto.response;

import com.hanhwa_tae.secondserver.common.domain.TargetType;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Getter
public class UserReviewDTO {
    private int reviewId;
    private int targetId;
    private TargetType targetType;
    private String detail;
    private Timestamp createdAt;
    private BigDecimal rating;
}
