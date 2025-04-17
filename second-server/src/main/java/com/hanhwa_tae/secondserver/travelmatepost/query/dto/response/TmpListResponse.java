package com.hanhwa_tae.secondserver.travelmatepost.query.dto.response;

import com.hanhwa_tae.secondserver.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TmpListResponse {
    private final List<TmpDTO> tmpList;
    private final Pagination pagination;
}
