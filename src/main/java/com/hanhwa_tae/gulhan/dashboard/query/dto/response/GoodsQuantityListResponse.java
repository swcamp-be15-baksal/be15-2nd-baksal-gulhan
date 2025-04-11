package com.hanhwa_tae.gulhan.dashboard.query.dto.response;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GoodsQuantityListResponse {

    private final List<GoodsQuantityDTO>  goodsQuantityList;

    private final Pagination pagination;
}
