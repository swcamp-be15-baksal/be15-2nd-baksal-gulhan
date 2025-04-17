package com.hanhwa_tae.firstserver.payment.query.controller;


import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderDetailResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderHistoryDetailResponse;
import com.hanhwa_tae.firstserver.payment.query.service.SearchPaymentService;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class SearchItemController {
    private final SearchPaymentService searchPaymentService;
    /*구매내역 조회*/
    @GetMapping("/order_history/{order_id}")
    public ResponseEntity<ApiResponse<OrderHistoryDetailResponse>> searchOrderHistory(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable String order_id
            ){
        Long userNo = userDetail.getUserNo();
        OrderHistoryDetailResponse orderHistoryDetail = searchPaymentService.findHistory(order_id);

        return ResponseEntity.ok(ApiResponse.success(orderHistoryDetail));
    }
    /*주문 정보 조회*/
    @GetMapping("/info")
    public ResponseEntity<ApiResponse<OrderDetailResponse>> searchOrder(
            @AuthenticationPrincipal CustomUserDetail userDetail

    ){
        Long userNo = userDetail.getUserNo();
        OrderDetailResponse orders = searchPaymentService.findOrder(userNo);

        return ResponseEntity.ok(ApiResponse.success(orders));
    }

/*    *//*환불 정보 조회*//*
    @GetMapping("/refund_info")
    public ResponseEntity<ApiResponse<RefundResponseDetail>> searchRefund(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){

    }*/
}
