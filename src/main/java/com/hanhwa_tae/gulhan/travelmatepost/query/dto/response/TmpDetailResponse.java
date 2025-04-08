package com.hanhwa_tae.gulhan.travelmatepost.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TmpDetailResponse {
    private final TmpDTO tmpDTO;
    private final List<CommentDTO> commentDTO;
}
