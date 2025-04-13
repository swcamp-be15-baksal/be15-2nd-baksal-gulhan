package com.hanhwa_tae.gulhan.cart.query.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartDetailResponse;
import com.hanhwa_tae.gulhan.cart.query.service.CartQueryService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "장바구니")
@RequestMapping("/api/v1/carts")
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
