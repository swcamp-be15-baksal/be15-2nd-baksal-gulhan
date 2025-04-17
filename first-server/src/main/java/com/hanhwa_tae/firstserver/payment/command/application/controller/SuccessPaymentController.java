package com.hanhwa_tae.firstserver.payment.command.application.controller;


import com.hanhwa_tae.firstserver.cart.command.domain.repository.CartRepository;
import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.payment.command.application.service.PaymentService;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import com.hanhwa_tae.firstserver.cart.command.application.service.OrderCommandService;
import com.hanhwa_tae.firstserver.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.firstserver.cart.query.mapper.CartMapper;
import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.payment.command.application.dto.request.CreateOrderRequest;
import com.hanhwa_tae.firstserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/payment/success")
@RequiredArgsConstructor
@RestController
public class SuccessPaymentController {
    private final CartMapper cartMapper;
    private final UserMapper userMapper;
    private final OrderCommandService orderCommandService;
    private final CartRepository cartRepository;
    private final PaymentService paymentService;


    /*주문 정보 및 구매내역 등록*/
    @PostMapping("/order")
    public ApiResponse<String> createMyOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        String userId = userDetail.getUserId();
        int percent = userMapper.findALLRankInfo(userDetail.getUserNo()).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND))
                .getPointRate();
        int point = (int)(createOrderRequest.getTotalPrice() * (percent / 100.0));
        /*주문 내역 저장 등록*/
        return ApiResponse.success(
                orderCommandService.registerOrder(userId, createOrderRequest, point)
        );
    }

    /*장바구니 내용을 바탕으로 구매내역 저장*/
    @PostMapping("/order-history")
    public ApiResponse<String> createMyOrderHistory(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        Long userNo = userDetail.getUserNo();
        List<CartResponse> cartList = cartMapper.selectAllCartByUserNo(userNo);

        return ApiResponse.success(
                orderCommandService.registerOrderHistory(userNo, cartList)
        );

    }

    /*구매한 품목 만큼 수량 감소 */
    @PutMapping
    public ApiResponse<String> updateCountByProductId(@AuthenticationPrincipal CustomUserDetail userDetail){
//        장바구니 내용을 바탕으로 패키지, 기념품 감소 시키기
        Long userNo = userDetail.getUserNo();
        List<CartResponse> carts = cartMapper.selectAllCartByUserNo(userNo);
        String result = "";
        for(CartResponse cart : carts){
            if(cart.getTargetType().name().equals("Goods")){
                result = orderCommandService.updateGoodsCountOfTargetId(cart.getQuantity(),cart.getTargetId());
            }
            else {
                result = orderCommandService.updatePackagesCountOfTargetId(cart.getQuantity(), cart.getTargetId());
            }
        }

        return ApiResponse.success(result);

    }
    /*장바구니 품목 모두 삭제*/
    @PutMapping("/delete")
    public void deleteAllItem(@AuthenticationPrincipal CustomUserDetail userDetail){
        /*위의 과정이 다 끝난 이후에 장바구니 모두 삭제 -> 컬럼 변화*/
        cartRepository.deleteAllByUserNo(userDetail.getUserNo());
    }


    /*구매 확정*/
    @PutMapping("/comfirm-purchase/{order_id}")
    public ApiResponse<String> updateIsConfirmed(@AuthenticationPrincipal CustomUserDetail userDetail,
                                                 @PathVariable String order_id) {
        Long userNo = userDetail.getUserNo();


        return ApiResponse.success(paymentService.updateConfirm(userNo, order_id));
    }




}


