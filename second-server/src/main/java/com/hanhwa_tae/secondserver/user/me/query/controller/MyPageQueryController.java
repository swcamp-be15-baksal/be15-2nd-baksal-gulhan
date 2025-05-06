package com.hanhwa_tae.secondserver.user.me.query.controller;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.domain.TargetType;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.delivery.query.dto.response.DeliveryAddressDTO;
import com.hanhwa_tae.secondserver.delivery.query.dto.response.DeliveryAddressResponse;
import com.hanhwa_tae.secondserver.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.me.query.dto.PackageOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.me.query.service.MyPageQueryService;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserBoardResponse;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserCommentResponse;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "내 동행글  조회", description = "회원은 내 동행글을 확인할 수 있다.")
    @GetMapping("/board")
    public ResponseEntity<ApiResponse<UserBoardResponse>> getUserBoard(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        UserBoardResponse response = myPageQueryService.getUserBoard(userDetail);

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
    public ResponseEntity<ApiResponse<DeliveryAddressResponse>> getDeliveryAddressList(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        DeliveryAddressResponse response = myPageQueryService.getDeliveryAddressList(userDetail);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "배송지 ID로 개별 배송지 조회", description = "배송지 ID로 단일 배송지 정보를 확인할 수 있다.")
    @GetMapping("/delivery-address/{deliveryAddressId}")
    public ResponseEntity<ApiResponse<DeliveryAddressDTO>> getDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable int deliveryAddressId
    ){
        DeliveryAddressDTO response = myPageQueryService.getDeliveryAddress(userDetail, deliveryAddressId);

        return ResponseEntity.ok(ApiResponse.success(response));
    }


}
