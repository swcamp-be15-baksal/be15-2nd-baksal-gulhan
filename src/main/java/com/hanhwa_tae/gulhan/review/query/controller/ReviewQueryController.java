package com.hanhwa_tae.gulhan.review.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.review.query.dto.request.ReviewSearchRequest;
import com.hanhwa_tae.gulhan.review.query.dto.response.ReviewListResponse;
import com.hanhwa_tae.gulhan.review.query.service.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "리뷰")
@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewQueryController {
    private final ReviewQueryService reviewQueryService;

    @Operation(summary = "리뷰 조회", description = "리뷰를 조회한다.")
    @GetMapping
    public ResponseEntity<ApiResponse<ReviewListResponse>> getReview(@ModelAttribute ReviewSearchRequest request) {
        ReviewListResponse response = reviewQueryService.getReview(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
