package com.hanhwa_tae.gulhan.user.me.query.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.dto.PackageOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.service.MyPageQueryService;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserCommentResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class MyPageQueryController {

    private final MyPageQueryService myPageQueryService;

    // 패키지 주문 내역 조회
    @GetMapping("/package/orders")
    public ResponseEntity<ApiResponse<List<PackageOrderHistoryDTO>>> getPackageOrderHistory(
            @AuthenticationPrincipal CustomUserDetail user
    ) {
        Long userNo = user.getUserNo();
        List<PackageOrderHistoryDTO> response = myPageQueryService.getPackageOrderHistory(userNo);

        if (response.isEmpty()) {
            throw new BusinessException(ErrorCode.ORDER_HISTORY_NOT_FOUND);
        }

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 기념품 주문 내역 조회
    @GetMapping("/goods/orders")
    public ResponseEntity<ApiResponse<List<GoodsOrderHistoryDTO>>> getGoodsOrderHistory(
            @AuthenticationPrincipal CustomUserDetail user
    ) {
        Long userNo = user.getUserNo();
        List<GoodsOrderHistoryDTO> response = myPageQueryService.getGoodsOrderHistory(userNo);

        if (response.isEmpty()) {
            throw new BusinessException(ErrorCode.ORDER_HISTORY_NOT_FOUND);
        }

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/review")
    public ResponseEntity<ApiResponse<UserReviewResponse>> getUserReview(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestParam TargetType targetType
    ){
        UserReviewResponse response = myPageQueryService.getUserReview(userDetail, targetType);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/comment")
    public ResponseEntity<ApiResponse<UserCommentResponse>> getUserComment(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        UserCommentResponse response = myPageQueryService.getUserComment(userDetail);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
