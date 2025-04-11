package com.hanhwa_tae.gulhan.review.query.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReviewUpdateRequest {

    private String detail;

    private BigDecimal rating;
}
