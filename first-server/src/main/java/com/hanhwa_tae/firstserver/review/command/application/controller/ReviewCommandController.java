package com.hanhwa_tae.firstserver.review.command.application.controller;


import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.review.command.application.service.ReviewCommandService;
import com.hanhwa_tae.firstserver.review.query.dto.request.ReviewInsertRequest;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "리뷰")
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewCommandController {
    private final ReviewCommandService reviewCommandService;

    @Operation(summary = "리뷰 작성", description = "해당 리뷰를 작성한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> insertReview(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @RequestBody ReviewInsertRequest request
    ) {
        Long userNo = customUserDetail.getUserNo();
        return ResponseEntity.ok(ApiResponse.success(reviewCommandService.insertReview(userNo, request)));
    }

    @Operation(summary = "리뷰 수정", description = "해당 리뷰를 수정한다.")
    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> updateReview (
            @PathVariable Integer reviewId
            , @AuthenticationPrincipal CustomUserDetail userDetail
            , @RequestBody ReviewInsertRequest request) {

        Long userNo = userDetail.getUserNo();
        reviewCommandService.updateReview(reviewId,userNo, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "리뷰 삭제", description = "해당 리뷰를 삭제한다.")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Integer reviewId, @RequestParam Long userNo) {
        reviewCommandService.deleteReview(reviewId, userNo);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
