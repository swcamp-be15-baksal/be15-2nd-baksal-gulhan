package com.hanhwa_tae.gulhan.cart.command.application.controller;


import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.application.dto.response.CreateCartResponse;
import com.hanhwa_tae.gulhan.cart.command.application.service.CartCommandService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartCommandController {
    private final CartCommandService cartCommandService;
    // 장바구니 품목 등록
    @PostMapping
    public ResponseEntity<ApiResponse<CreateCartResponse>> createMyCart(
            @RequestBody @Validated CreateCartRequest createCartRequest,
            @AuthenticationPrincipal CustomUserDetail customUserDetail){

        String id = customUserDetail.getUserId();
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
    public ResponseEntity<ApiResponse<Void>> updateMyCart(@PathVariable int cart_id,
                                                          @RequestBody UpdateCartRequest updateCartRequest,
                                                          @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        String id = userDetail.getUserId();
        cartCommandService.updateCart(id, cart_id, updateCartRequest);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 장바구니 내 특정 상품 삭제
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<ApiResponse<Void>> deleteMyCart(@PathVariable int cart_id,
                                                          @AuthenticationPrincipal CustomUserDetail userDetail){
        String id = userDetail.getUserId();
        cartCommandService.deleteCart(id, cart_id);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
    // 장바구니 모든 품목 삭제
    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteAllMyCart(@AuthenticationPrincipal CustomUserDetail userDetail){
        String id = userDetail.getUserId();
        cartCommandService.deleteAllCart(id);
        return ResponseEntity.ok(ApiResponse.success(null));

    }
}
