package com.hanhwa_tae.gulhan.goods.query.dto.response;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class GoodsListResponse {
    private final List<GoodsDTO> goods;
    private final Pagination pagination;
}
