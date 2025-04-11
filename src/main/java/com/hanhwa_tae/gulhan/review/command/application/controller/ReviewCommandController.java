package com.hanhwa_tae.gulhan.review.command.application.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.packages.command.application.service.PackageCommandService;
import com.hanhwa_tae.gulhan.review.command.application.service.ReviewCommandService;
import com.hanhwa_tae.gulhan.review.query.dto.request.ReviewInsertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewCommandController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> insertReview(@RequestBody ReviewInsertRequest request) {
        return ResponseEntity.ok(ApiResponse.success(reviewCommandService.insertReview(request)));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> updateReview (@PathVariable Integer reviewId, @RequestBody ReviewInsertRequest request) {
        reviewCommandService.updateReview(reviewId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Integer reviewId, @RequestParam Long userNo) {
        reviewCommandService.deleteReview(reviewId, userNo);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
