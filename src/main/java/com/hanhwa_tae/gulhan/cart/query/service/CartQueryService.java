package com.hanhwa_tae.gulhan.cart.query.service;

import com.hanhwa_tae.gulhan.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartDetailResponse;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.gulhan.cart.query.mapper.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartQueryService {
    private final CartMapper cartMapper;
    @Transactional(readOnly = true)
    public CartDetailResponse getMyCart(int userNo, CartSearchRequest cartSearchRequest){
        List<CartResponse> carts = cartMapper.selectCartsByUserId(userNo, cartSearchRequest);
        long totalItems = cartMapper.countCarts(userNo);
        int page = cartSearchRequest.getPage();
        int size = cartSearchRequest.getSize();


        return CartDetailResponse.builder().
                build();

    }
}
