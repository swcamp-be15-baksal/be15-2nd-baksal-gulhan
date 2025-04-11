package com.hanhwa_tae.gulhan.travelmatepost.query.dto.response;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommentListResponse {
    private final List<CommentDTO> commentDTO;
    private final Pagination pagination;
}
