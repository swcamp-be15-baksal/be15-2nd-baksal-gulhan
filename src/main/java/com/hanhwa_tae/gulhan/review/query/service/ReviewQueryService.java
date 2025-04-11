package com.hanhwa_tae.gulhan.review.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.review.query.dto.request.ReviewSearchRequest;
import com.hanhwa_tae.gulhan.review.query.dto.response.ReviewDTO;
import com.hanhwa_tae.gulhan.review.query.dto.response.ReviewListResponse;
import com.hanhwa_tae.gulhan.review.query.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {
    private final ReviewMapper reviewMapper;

    @Transactional(readOnly = true)
    public ReviewListResponse getReview(ReviewSearchRequest reviewSearchRequest) {
        reviewSearchRequest.calculatePaging();

        List<ReviewDTO> review = reviewMapper.selectReview(reviewSearchRequest);
        long totalReview = reviewMapper.countReview(reviewSearchRequest);

        int page = reviewSearchRequest.getPage();
        int size = reviewSearchRequest.getSize();

        return ReviewListResponse.builder()
                .review(review)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int)Math.ceil((double)totalReview/size))
                        .size(size)
                        .build())
                .build();
    }

}
