package com.hanhwa_tae.gulhan.travelmatepost.query.dto.response;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TmpListResponse {
    private final List<TmpDTO> tmpList;
    private final Pagination pagination;
}
