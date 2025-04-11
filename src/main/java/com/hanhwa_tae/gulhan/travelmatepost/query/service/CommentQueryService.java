package com.hanhwa_tae.gulhan.travelmatepost.query.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.request.CommentSearchRequest;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.CommentDTO;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.CommentListResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentMapper commentMapper;

    public CommentListResponse getComment(int travelMatePostId, CommentSearchRequest commentSearchRequest) {

        List<CommentDTO> comment = Optional.ofNullable(
                        commentMapper.selectCommentByTravelMatePostId(travelMatePostId))
                .orElseThrow(()->new RuntimeException("댓글 없음"));

        long totalPosts = commentMapper.commentCount(travelMatePostId);

        int page = commentSearchRequest.getPage();
        int size = commentSearchRequest.getSize();

        return CommentListResponse.builder()
                .commentDTO(comment)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .size(size)
                        .build())
                .build();
    }

}
