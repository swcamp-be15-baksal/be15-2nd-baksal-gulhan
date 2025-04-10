package com.hanhwa_tae.gulhan.cart.query.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartDetailResponse;
import com.hanhwa_tae.gulhan.cart.query.service.CartQueryService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/api/v1/carts")
@RestController
@RequiredArgsConstructor
public class CartQueryController {
    private final CartQueryService cartQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<CartDetailResponse>> getCarts(
            @AuthenticationPrincipal CustomUserDetail userDetail, CartSearchRequest cartSearchRequest
    ){
        String id = userDetail.getUserId();
        CartDetailResponse response = cartQueryService.getMyCart(id, cartSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));

    }
}
