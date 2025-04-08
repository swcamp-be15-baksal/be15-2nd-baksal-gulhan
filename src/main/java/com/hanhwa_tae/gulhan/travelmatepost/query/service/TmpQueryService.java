package com.hanhwa_tae.gulhan.travelmatepost.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.request.TmpSearchRequest;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.CommentDTO;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpDTO;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpDetailResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpListResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.mapper.CommentMapper;
import com.hanhwa_tae.gulhan.travelmatepost.query.mapper.TmpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TmpQueryService {

    private final TmpMapper tmpMapper;
    private final CommentMapper commentMapper;

    /* 게시판 목록 조회 */
    public TmpListResponse getTmpList(TmpSearchRequest tmpSearchRequest) {

        List<TmpDTO> tmpDTOList = tmpMapper.selectTravelMatePosts(tmpSearchRequest);

        long totalPosts = tmpMapper.countPosts(tmpSearchRequest);

        int page = tmpSearchRequest.getPage();
        int size = tmpSearchRequest.getSize();

        return TmpListResponse.builder()
                .tmpList(tmpDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalSize((int) Math.ceil((double) totalPosts / size))
                        .totalPosts(totalPosts)
                        .build())
                .build();
    }

    /* 동행글 상세 조회 */
    public TmpDetailResponse getTmpList(int travelMatePostId) {

        int comments = travelMatePostId;

        TmpDTO tmp = Optional.ofNullable(
                tmpMapper.selectTmpByTravelMatePostId(travelMatePostId))
                .orElseThrow(() -> new RuntimeException("글 없음"));

        List<CommentDTO> comment = Optional.ofNullable(
                commentMapper.selectCommentByTravelMatePostId(comments))
                .orElseThrow(()->new RuntimeException("댓글 없음"));

        List<CommentDTO> commentTree = buildCommentTree(comment);

        return TmpDetailResponse.builder()
                .tmpDTO(tmp)
                .commentDTO(commentTree)
                .build();
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
