package com.hanhwa_tae.gulhan.cart.command.application.controller;


import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.application.dto.response.CreateCartResponse;
import com.hanhwa_tae.gulhan.cart.command.application.service.CartCommandService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartCommandController {
    private final CartCommandService cartCommandService;
    // 장바구니 품목 등록
    @PostMapping
    public ResponseEntity<ApiResponse<CreateCartResponse>> createMyCart(
            @RequestBody @Validated CreateCartRequest createCartRequest,
            Principal principal){

        String id = principal.getName();

        int cartId = cartCommandService.registerCart(id,createCartRequest);
        CreateCartResponse response = CreateCartResponse.
                builder().
                cartId(cartId).
                build();

        return ResponseEntity.status(HttpStatus.CREATED).
                body(ApiResponse.success(response));
    }

    // 장바구니 수량 수정
    @PutMapping("/{cart_id}")
    public ResponseEntity<ApiResponse<Void>> updateMyCart(@PathVariable int cart_Id, @RequestBody UpdateCartRequest updateCartRequest){
        cartCommandService.updateCart(cart_Id, updateCartRequest);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 장바구니 내 특정 상품 삭제
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<ApiResponse<Void>> deleteMyCart(@PathVariable int cart_id){
        cartCommandService.deleteCart(cart_id);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
    // 장바구니 모든 품목 삭제
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllMyCart(){
        // 유저 로그인 후 정보 받아오는 거 확인하고..
//        cartCommandService.deleteAllCart();
        return ResponseEntity.ok(ApiResponse.success(null));

    }
}
