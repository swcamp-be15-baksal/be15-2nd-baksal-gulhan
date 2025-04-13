package com.hanhwa_tae.gulhan.like.query.dto.response;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LikeListResponse {
    private final List<LikeDto> likes;
    private final Pagination pagination;
}
