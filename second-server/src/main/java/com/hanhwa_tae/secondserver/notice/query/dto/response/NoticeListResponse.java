package com.hanhwa_tae.secondserver.notice.query.dto.response;

import com.hanhwa_tae.secondserver.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class NoticeListResponse {
    private final List<NoticeListDTO> tmpList;
    private final Pagination pagination;
}