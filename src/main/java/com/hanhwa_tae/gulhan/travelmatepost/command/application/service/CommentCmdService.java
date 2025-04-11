package com.hanhwa_tae.gulhan.travelmatepost.command.application.service;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.CommentInsertRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.CommentUpdateRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate.Comment;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate.TravelMatePost;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.repository.JpaCommentRepository;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.repository.JpaTravelMatePostRepository;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentCmdService {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final JpaCommentRepository jpaCommentRepository;
    private final JpaTravelMatePostRepository  jpaTravelMatePostRepository;

    /* 댓글 등록 */
    @Transactional
    public void insertComment(String id, int travelMatePostId, CommentInsertRequest commentInsertRequest) {

        User user = userMapper.findUserByUserId(id)
                .orElseThrow( () -> new RuntimeException("회원 없음"));

        TravelMatePost travelMatePost = jpaTravelMatePostRepository.findById(travelMatePostId)
                .orElseThrow( () -> new RuntimeException("게시글 없음"));

        Comment parentComment;
        if(commentInsertRequest.getParentCommentId() != null) {
            parentComment = jpaCommentRepository.findById(commentInsertRequest.getParentCommentId())
                    .orElseThrow( () -> new RuntimeException("부모 댓글 없음"));
        } else {
            parentComment = null;
        }

        Comment comment = modelMapper.map(commentInsertRequest, Comment.class);

        comment.setTravelMatePostId(travelMatePost);
        comment.setUserNo(user);
        comment.setParentCommentId(parentComment);

        jpaCommentRepository.save(comment);
    }

    /* 댓글 수정 */
    @Transactional
    public void updateComment(CommentUpdateRequest commentUpdateRequest) {


    }

    /* 댓글 삭제 */
    @Transactional
    public void deleteComment(int commentId) {
        jpaCommentRepository.deleteById(commentId);
    }

}
