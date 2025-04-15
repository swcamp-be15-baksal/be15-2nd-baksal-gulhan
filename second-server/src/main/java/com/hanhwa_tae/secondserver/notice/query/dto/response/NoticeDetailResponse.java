package com.hanhwa_tae.secondserver.notice.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeDetailResponse {
    private final NoticeDetailDTO noticeDTO;
}
