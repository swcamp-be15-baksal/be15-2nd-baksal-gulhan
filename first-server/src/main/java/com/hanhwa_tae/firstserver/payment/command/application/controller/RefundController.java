package com.hanhwa_tae.firstserver.payment.command.application.controller;


import com.hanhwa_tae.firstserver.cart.command.application.service.OrderCommandService;
import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.payment.command.application.dto.request.CreateRefundRequest;
import com.hanhwa_tae.firstserver.payment.command.application.service.RefundCommandService;
import com.hanhwa_tae.firstserver.review.query.mapper.OrderMapper;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import com.hanhwa_tae.firstserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/payment/refund")
@RequiredArgsConstructor
@RestController
public class RefundController {
    private final OrderMapper orderMapper;
    private final OrderCommandService orderCommandService;
    private final UserMapper userMapper;
    private final RefundCommandService refundCommandService;

    /* 환불하면 구매내역 수량 만큼 다 증가하기*/
    @PutMapping
    public ApiResponse<String> refundItem(
            @AuthenticationPrincipal CustomUserDetail userDetail
            ){
        Long userNo = userDetail.getUserNo();

        return ApiResponse.success(
                orderCommandService.updateItemCount(userNo)
        );
    }

    /* 환불하면, 환불주문 등록 -> 주문 Id로 비교해서 환불 내역에 있으면 안 보이게*/
    @PostMapping
    public ApiResponse<String> createRefund(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody CreateRefundRequest createRefundRequest
    ){
//        Long userNo = userDetail.getUserNo();

        return ApiResponse.success(refundCommandService.createRefund(createRefundRequest));

    }
}
