package com.hanhwa_tae.gulhan.notice.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeListDTO {

    private int noticeId;

    private String title;

    private String createdAt;

    private String userId;
}
