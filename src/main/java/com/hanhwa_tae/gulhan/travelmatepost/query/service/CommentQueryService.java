package com.hanhwa_tae.gulhan.travelmatepost.query.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.request.CommentSearchRequest;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.CommentDTO;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.CommentListResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentQueryService {

    private final CommentMapper commentMapper;

    /* 해당 동행글 댓글 조회
    *
    * */
    public CommentListResponse getComment(CustomUserDetail userDetail, int travelMatePostId, CommentSearchRequest commentsSearchRequest) {
        return null;
    }

    /* 트리 구조로 구현 (commentId)
     * 1번 댓글 (1)
     *    1번 대댓글 (2)
     *       대댓글에 댓글 (5)
     *    1번 대댓글 (3)
     * 2번 댓글 (4)
     * */
    public List<CommentDTO> buildCommentTree(List<CommentDTO> comments) {
        Map<Integer, CommentDTO> commentMap = new HashMap<>();
        List<CommentDTO> rootComments = new ArrayList<>();

        /*댓글마다 자식 댓글 리스트를 담기*/
        for (CommentDTO comment : comments) {
            comment.setChildren(new ArrayList<>());
            commentMap.put(comment.getCommentId(), comment);
        }

        for (CommentDTO comment : comments) {
            if (comment.getParentCommentId() == null) {
                rootComments.add(comment);
            } else {
                CommentDTO parent = commentMap.get(comment.getParentCommentId());
                if (parent != null) {
                    parent.getChildren().add(comment);
                }
            }
        }
        return rootComments;
    }
}
