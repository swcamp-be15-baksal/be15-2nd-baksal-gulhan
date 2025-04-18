package com.hanhwa_tae.firstserver.payment.query.controller;


import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderDetailResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.OrderHistoryDetailResponse;
import com.hanhwa_tae.firstserver.payment.query.dto.response.RefundResponseDetail;
import com.hanhwa_tae.firstserver.payment.query.service.SearchPaymentService;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "구매내역 조회", description = "사용자는 본인의 구매내역을 order_id를 통해 조회한다.")
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
    @Operation(summary = "주문내역 조회", description = "사용자는 본인의 구매내역 정보를 확인한다.")
    @GetMapping("/info")
    public ResponseEntity<ApiResponse<OrderDetailResponse>> searchOrder(
            @AuthenticationPrincipal CustomUserDetail userDetail

    ){
        Long userNo = userDetail.getUserNo();
        OrderDetailResponse orders = searchPaymentService.findOrder(userNo);

        return ResponseEntity.ok(ApiResponse.success(orders));
    }

    /*추가로 구현해야되는 부분*/
    /*@Operation(summary = "환불 내역 조회",description = "사용자는 본인의 환불내역을 확인할 수 있다.")
    @GetMapping("/refund_info")
    public ResponseEntity<ApiResponse<RefundResponseDetail>> searchRefund(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){


    }*/
}
