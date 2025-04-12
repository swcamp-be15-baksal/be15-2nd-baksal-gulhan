package com.hanhwa_tae.gulhan.user.query.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.review.command.domain.aggregate.Review;
import com.hanhwa_tae.gulhan.user.query.dto.UserReviewDTO;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserInfoResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserReviewResponse;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserMapper userMapper;

    @Override
    public UserInfoResponse getUserInfo(CustomUserDetail userDetail) {
        // TODO : .authenticated 사용 시 필요 없는지 확인하기!
        if (userDetail == null) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }

        String userId = userDetail.getUserId();
        UserInfoResponse user = userMapper.findUserInfoByUserId(userId).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );

        return user;
    }

    @Override
    public UserReviewResponse getUserReview(CustomUserDetail userDetail, TargetType targetType) {

        if (targetType.equals(TargetType.PLACE)) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED_REQUEST);
        }

        Long userNo = userDetail.getUserNo();

        List<Review> userReview = userMapper.findReviewByUserNoAndTargetType(userNo, targetType);

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
}
