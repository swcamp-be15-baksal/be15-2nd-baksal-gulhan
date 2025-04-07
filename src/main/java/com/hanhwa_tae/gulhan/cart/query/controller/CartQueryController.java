package com.hanhwa_tae.gulhan.cart.query.controller;

import com.hanhwa_tae.gulhan.cart.query.service.CartQueryService;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/carts")
@RestController
@RequiredArgsConstructor
public class CartQueryController {
    private final CartQueryService cartQueryService;

    @GetMapping("/{user_id}")
    public ResponseEntity<ApiResponse<Cart>>
}
