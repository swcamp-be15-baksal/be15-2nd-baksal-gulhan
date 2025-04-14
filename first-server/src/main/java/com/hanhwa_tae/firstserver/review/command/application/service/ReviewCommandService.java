package com.hanhwa_tae.firstserver.review.command.application.service;


import com.hanhwa_tae.firstserver.common.domain.DeleteType;
import com.hanhwa_tae.firstserver.review.command.domain.aggregate.Review;
import com.hanhwa_tae.firstserver.review.command.domain.repository.JpaReviewRepository;
import com.hanhwa_tae.firstserver.review.query.dto.request.ReviewInsertRequest;
import com.hanhwa_tae.firstserver.review.query.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final ModelMapper modelMapper;
    private final JpaReviewRepository jpaReviewRepository;

    private final OrderMapper orderMapper;

    @Transactional
    public int insertReview(Long userNo, ReviewInsertRequest request) {
        boolean isConfirmed = orderMapper.existsConfirmedOrder(userNo, request.getTargetId(), request.getTargetType());
        if (!isConfirmed) {
            throw new SecurityException("리뷰를 작성할 권한이 없습니다."); // 유저가 해당 상품을 구매확정한 이력 없음
        }

        Review review = modelMapper.map(request, Review.class);
        review.setUserNo(userNo);
        Review newReview = jpaReviewRepository.save(review);

        return newReview.getReviewId();
    }

    @Transactional
    public void updateReview(Integer reviewId, Long userNo, ReviewInsertRequest request) {
        Review review = jpaReviewRepository.findById(reviewId).orElseThrow(
                () -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다.")
        );

        // 작성자 확인
        if (!review.getUserNo().equals(userNo)) {
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

        if (!review.getUserNo().equals(userNo)) {
            throw new SecurityException("리뷰를 삭제할 권한이 없습니다.");
        }

        review.setIsDeleted(DeleteType.Y);
    }
}
