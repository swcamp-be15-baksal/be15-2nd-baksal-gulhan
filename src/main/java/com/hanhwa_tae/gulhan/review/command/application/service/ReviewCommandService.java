package com.hanhwa_tae.gulhan.review.command.application.service;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import com.hanhwa_tae.gulhan.review.command.domain.aggregate.Review;
import com.hanhwa_tae.gulhan.review.command.domain.repository.JpaReviewRepository;
import com.hanhwa_tae.gulhan.review.query.dto.request.ReviewInsertRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {
    private final ModelMapper modelMapper;
    private final JpaReviewRepository jpaReviewRepository;

    @Transactional
    public int insertReview(ReviewInsertRequest request) {
        Review review = modelMapper.map(request, Review.class);
        Review newReview = jpaReviewRepository.save(review);

        return newReview.getReviewId();
    }

    @Transactional
    public void updateReview(Integer reviewId, ReviewInsertRequest request) {
        Review review = jpaReviewRepository.findById(reviewId).orElseThrow();
        review.updateReview(
                request.getDetail(),
                request.getRating()
        );
    }

    @Transactional
    public void deleteReview(Integer reviewId) {
        Review review = jpaReviewRepository.findById(reviewId).orElseThrow();
        review.setIsDeleted(DeleteType.Y);
    }
}
