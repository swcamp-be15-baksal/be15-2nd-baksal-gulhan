package com.hanhwa_tae.gulhan.travelmatepost.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TmpDetailDTO {
    private int travelMatePostId;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private String userId;
}
