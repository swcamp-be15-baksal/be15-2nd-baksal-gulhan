package com.hanhwa_tae.secondserver.user.me.query.controller;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.domain.TargetType;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.delivery.query.dto.response.DeliveryAddressResponse;
import com.hanhwa_tae.secondserver.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.me.query.dto.PackageOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.me.query.service.MyPageQueryService;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserCommentResponse;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "마이 페이지")
@RestController
@RequestMapping("/users/me")
@RequiredArgsConstructor
public class MyPageQueryController {

    private final MyPageQueryService myPageQueryService;

    @Operation(summary = "내 패키지 주문 내역 조회", description = "회원은 내 패키지 주문 내역을 확인할 수 있다.")
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
    @Operation(summary = "내 기념품 주문 내역 조회", description = "회원은 내 기념품 주문 내역을 확인할 수 있다.")
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

    @Operation(summary = "내 리뷰 조회", description = "회원은 내 리뷰를 확인할 수 있다.")
    @GetMapping("/review")
    public ResponseEntity<ApiResponse<UserReviewResponse>> getUserReview(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestParam TargetType targetType
    ){
        UserReviewResponse response = myPageQueryService.getUserReview(userDetail, targetType);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "내 동행글 댓글 조회", description = "회원은 내 동행글 댓글을 확인할 수 있다.")
    @GetMapping("/comment")
    public ResponseEntity<ApiResponse<UserCommentResponse>> getUserComment(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        UserCommentResponse response = myPageQueryService.getUserComment(userDetail);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "배송지 목록 조회", description = "회원은 자신이 등록한 배송지의 목록을 확인할 수 있다.")
    @GetMapping("/delivery-address")
    public ResponseEntity<ApiResponse<DeliveryAddressResponse>> getDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        DeliveryAddressResponse response = myPageQueryService.getDeliveryAddress(userDetail);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
