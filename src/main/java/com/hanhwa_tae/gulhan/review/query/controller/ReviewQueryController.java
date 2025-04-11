package com.hanhwa_tae.gulhan.review.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.review.query.dto.request.ReviewSearchRequest;
import com.hanhwa_tae.gulhan.review.query.dto.response.ReviewListResponse;
import com.hanhwa_tae.gulhan.review.query.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewQueryController {
    private final ReviewQueryService reviewQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<ReviewListResponse>> getReview(@ModelAttribute ReviewSearchRequest request) {
        ReviewListResponse response = reviewQueryService.getReview(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
