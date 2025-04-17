package com.hanhwa_tae.firstserver.review.query.dto.response;


import com.hanhwa_tae.firstserver.common.domain.DeleteType;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class ReviewDTO {
    private int reviewId;

    private int targetId;

    private TargetType targetType;

    private String detail;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private BigDecimal rating;

    private DeleteType isDeleted;

    private Long userNo;
}
