package com.hanhwa_tae.gulhan.review.query.dto.response;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
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

    private User userNo;
}
