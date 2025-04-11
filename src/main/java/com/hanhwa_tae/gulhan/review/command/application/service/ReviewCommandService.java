package com.hanhwa_tae.gulhan.review.command.application.service;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.review.command.domain.aggregate.Review;
import com.hanhwa_tae.gulhan.review.command.domain.repository.JpaReviewRepository;
import com.hanhwa_tae.gulhan.review.query.dto.request.ReviewInsertRequest;
import com.hanhwa_tae.gulhan.review.query.mapper.OrderMapper;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final ModelMapper modelMapper;
    private final JpaReviewRepository jpaReviewRepository;
    private final EntityManager entityManager;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;

    @Transactional
    public int insertReview(String id, ReviewInsertRequest request) {
        User userRef = entityManager.getReference(User.class, request.getUserNo());

        userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        boolean isConfirmed = orderMapper.existsConfirmedOrder(id, request.getTargetId(), request.getTargetType());
        if (!isConfirmed) {
            throw new SecurityException("리뷰를 작성할 권한이 없습니다."); // 유저가 해당 상품을 구매확정한 이력 없음
        }

        Review review = modelMapper.map(request, Review.class);
        review.setUserNo(userRef);
        Review newReview = jpaReviewRepository.save(review);

        return newReview.getReviewId();
    }

    @Transactional
    public void updateReview(Integer reviewId, ReviewInsertRequest request) {
        Review review = jpaReviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다.")
        );

        // 작성자 확인
        if (!review.getUserNo().getUserNo().equals(request.getUserNo())) {
            throw new SecurityException("리뷰를 수정할 권한이 없습니다.");
        }

        review.updateReview(
                request.getDetail(),
                request.getRating()
        );
    }

    @Transactional
    public void deleteReview(Integer reviewId, Long userNo) {
        Review review = jpaReviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다.")
        );

        if (!review.getUserNo().getUserNo().equals(userNo)) {
            throw new SecurityException("리뷰를 삭제할 권한이 없습니다.");
        }

        review.setIsDeleted(DeleteType.Y);
    }
}
