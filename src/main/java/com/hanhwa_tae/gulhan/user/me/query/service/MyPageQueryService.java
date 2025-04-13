package com.hanhwa_tae.gulhan.user.me.query.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.review.command.domain.aggregate.Review;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate.Comment;
import com.hanhwa_tae.gulhan.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.dto.PackageOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.mapper.MyPageMapper;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserCommentDTO;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserCommentResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserReviewDTO;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserReviewResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageQueryService {

    private final MyPageMapper myPageMapper;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<PackageOrderHistoryDTO> getPackageOrderHistory(Long userNo) {
        return myPageMapper.findPackageOrderHistoryByUserNo(userNo);
    }

    @Transactional(readOnly = true)
    public List<GoodsOrderHistoryDTO> getGoodsOrderHistory(Long userNo) {
        return myPageMapper.findGoodsOrderHistoryByUserNo(userNo);
    }

    @Transactional
    public void confirmOrder(int orderHistoryId) {
        int updateIsConfirmed = myPageMapper.updateIsConfirmed(orderHistoryId);
        if (updateIsConfirmed == 0) {
            throw new BusinessException(ErrorCode.ORDER_HISTORY_NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    public UserReviewResponse getUserReview(CustomUserDetail userDetail, TargetType targetType) {

        if (targetType.equals(TargetType.PLACE)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        Long userNo = userDetail.getUserNo();

        List<Review> userReview = myPageMapper.findReviewByUserNoAndTargetType(userNo, targetType);

        List<UserReviewDTO> responseReviewList = new ArrayList<>();

        for (Review review : userReview) {
            responseReviewList.add(UserReviewDTO.builder()
                    .reviewId(review.getReviewId())
                    .targetId(review.getTargetId())
                    .targetType(review.getTargetType())
                    .detail(review.getDetail())
                    .createdAt(review.getCreatedAt())
                    .rating(review.getRating())
                    .build()
            );
        }

        return UserReviewResponse.builder()
                .userReviewList(responseReviewList)
                .build();
    }

    @Transactional(readOnly = true)
    public UserCommentResponse getUserComment(CustomUserDetail userDetail) {

        Long userNo = userDetail.getUserNo();

        List<Comment> commentList = myPageMapper.findCommentByUserNo(userNo);

        List<UserCommentDTO> responseCommentList = new ArrayList<>();

        for(Comment comment : commentList){
            UserCommentDTO userCommentDto = modelMapper.map(comment, UserCommentDTO.class);
            responseCommentList.add(userCommentDto);
        }

        return UserCommentResponse.builder()
                .commentList(responseCommentList)
                .build();
    }

}
