package com.hanhwa_tae.secondserver.travelmatepost.command.application.service;

import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.CommentInsertRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.CommentUpdateRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.domain.aggregate.Comment;
import com.hanhwa_tae.secondserver.travelmatepost.command.domain.aggregate.TravelMatePost;
import com.hanhwa_tae.secondserver.travelmatepost.command.domain.repository.JpaCommentRepository;
import com.hanhwa_tae.secondserver.travelmatepost.command.domain.repository.JpaTravelMatePostRepository;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.RankType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
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
                .orElseThrow( () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        TravelMatePost travelMatePost = jpaTravelMatePostRepository.findById(travelMatePostId)
                .orElseThrow( () -> new BusinessException(ErrorCode.POST_NOT_FOUND));

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
    public void updateComment(String id, int commentId, CommentUpdateRequest commentUpdateRequest) {

        User user = userMapper.findUserByUserId(id)
                .orElseThrow( () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Comment newComment = jpaCommentRepository.findById(commentId)
                .orElseThrow( () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));

        if (!user.getUserNo().equals(newComment.getUserNo().getUserNo())) {
            throw new BusinessException(ErrorCode.COMMENT_NOT_OWNED);
        }

        Comment comment = modelMapper.map(newComment, Comment.class);

        comment.updateComment(
                commentUpdateRequest.getContent()
        );
    }

    /* 댓글 삭제 */
    @Transactional
    public void deleteComment(String id, int commentId) {

        User user = userMapper.findUserByUserId(id)
                .orElseThrow( () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Comment newComment = jpaCommentRepository.findById(commentId)
                .orElseThrow( () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND));

        if (!user.getUserNo().equals(newComment.getUserNo().getUserNo()) && !user.getRank().getRankName().equals(RankType.SLAVE)) {
            throw new BusinessException(ErrorCode.COMMENT_NOT_OWNED);
        }

        jpaCommentRepository.deleteById(newComment.getCommentId());
    }

}
