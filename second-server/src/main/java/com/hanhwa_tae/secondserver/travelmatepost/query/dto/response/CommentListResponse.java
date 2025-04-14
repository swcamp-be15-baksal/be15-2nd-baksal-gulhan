package com.hanhwa_tae.secondserver.travelmatepost.query.dto.response;

import com.hanhwa_tae.secondserver.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommentListResponse {
    private final List<CommentDTO> commentDTO;
    private final Pagination pagination;
}
