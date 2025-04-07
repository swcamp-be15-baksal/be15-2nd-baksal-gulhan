package com.hanhwa_tae.gulhan.cart.query.controller;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartDetailResponse;
import com.hanhwa_tae.gulhan.cart.query.service.CartQueryService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
public class CartQueryController {
    private final CartQueryService cartQueryService;

    @GetMapping("/{user_no}")
    public ResponseEntity<ApiResponse<CartDetailResponse>> getCarts(
            @PathVariable int user_no, CartSearchRequest cartSearchRequest
    ){
        CartDetailResponse response = cartQueryService.getMyCart(user_no, cartSearchRequest);

    }
}
