package com.hanhwa_tae.gulhan.cart.query.service;

import com.hanhwa_tae.gulhan.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartDetailResponse;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.gulhan.cart.query.mapper.CartMapper;
import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartQueryService {
    private final CartMapper cartMapper;
    @Transactional(readOnly = true)
    /* 내 장바구니 목록 조회*/
    public CartDetailResponse getMyCart(int userNo, CartSearchRequest cartSearchRequest){
        List<CartResponse> carts = cartMapper.selectCartsByUserId(userNo, cartSearchRequest);
        long totalItems = cartMapper.countCarts(userNo);
        int page = cartSearchRequest.getPage();
        int size = cartSearchRequest.getSize();


        return CartDetailResponse.builder().
                carts(carts).
                pagination(Pagination.builder().
                        currentPage(page).
                        page((int)Math.ceil((double) totalItems/size)).
                        size(size).build()).build();
    }
}
