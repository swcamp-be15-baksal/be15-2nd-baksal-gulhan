package com.hanhwa_tae.gulhan.review.query.dto.request;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ReviewSearchRequest {

    private Integer targetId;
    private TargetType tartgetType;

    private Integer page = 1;
    private Integer size = 20;

    private Integer limit;
    private Integer offset;

    public void calculatePaging() {
        this.limit = size;
        this.offset = (page-1) * size;
    }

}
