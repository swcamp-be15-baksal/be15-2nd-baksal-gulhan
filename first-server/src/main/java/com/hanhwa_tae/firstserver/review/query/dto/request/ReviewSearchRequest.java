package com.hanhwa_tae.firstserver.review.query.dto.request;

import com.hanhwa_tae.firstserver.common.domain.TargetType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReviewSearchRequest {

    private Integer targetId;
    private TargetType targetType;

    private Integer page = 1;
    private Integer size = 20;

    private Integer limit;
    private Integer offset;

    public void calculatePaging() {
        this.limit = size;
        this.offset = (page-1) * size;
    }

}
