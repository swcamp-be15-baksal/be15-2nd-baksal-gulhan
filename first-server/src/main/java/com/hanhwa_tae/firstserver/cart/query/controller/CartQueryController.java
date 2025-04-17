package com.hanhwa_tae.firstserver.cart.query.controller;

import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import com.hanhwa_tae.firstserver.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.firstserver.cart.query.dto.response.CartDetailResponse;
import com.hanhwa_tae.firstserver.cart.query.service.CartQueryService;
import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "장바구니")
@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
public class CartQueryController {
    private final CartQueryService cartQueryService;

    @Operation(summary = "장바구니 조회", description = "회원은 자신의 장바구니를 조회할 수 있다.")
    @GetMapping
    public ResponseEntity<ApiResponse<CartDetailResponse>> getCarts(
            @AuthenticationPrincipal CustomUserDetail userDetail, CartSearchRequest cartSearchRequest
    ){
        String id = userDetail.getUserId();
        CartDetailResponse response = cartQueryService.getMyCart(id, cartSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));

    }
}
