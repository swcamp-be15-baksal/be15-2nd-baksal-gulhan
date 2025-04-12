package com.hanhwa_tae.gulhan.payment.command.application.controller;


import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.payment.command.application.dto.response.CreateOrderResponse;
import com.hanhwa_tae.gulhan.payment.command.application.dto.request.CreateOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/payment/success")
@RequiredArgsConstructor
@RestController
public class SuccessPaymentController {

    /*주문 정보 및 구매내역 등록*/
    @PostMapping("/order")
    public String createMyOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        Long userNo = userDetail.getUserNo();

        return "ok";
    }


    /*구매한 품목 만큼 수량 감소*/
    @PutMapping
    public void updateCountByProductId(@AuthenticationPrincipal CustomUserDetail userDetail){

    }
    /*장바구니 품목 모두 삭제*/
    @DeleteMapping
    public void deleteAllItem(@AuthenticationPrincipal CustomUserDetail userDetail){

    }

}
