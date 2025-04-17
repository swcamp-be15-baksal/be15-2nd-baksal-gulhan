package com.hanhwa_tae.secondserver.cart.query.service;

import com.hanhwa_tae.secondserver.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.secondserver.cart.query.dto.response.CartDetailResponse;
import com.hanhwa_tae.secondserver.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.secondserver.cart.query.mapper.CartMapper;
import com.hanhwa_tae.secondserver.common.dto.Pagination;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartQueryService {
    private final CartMapper cartMapper;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    /* 내 장바구니 목록 조회*/
    public CartDetailResponse getMyCart(String id, CartSearchRequest cartSearchRequest){
        Long userNo = userMapper.findUserByUserId(id).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)).getUserNo();
        List<CartResponse> carts = cartMapper.selectCartsByUserNo(userNo, cartSearchRequest);

        
        long totalItems = cartMapper.countCarts(userNo);
        int page = cartSearchRequest.getPage();
        int size = cartSearchRequest.getSize();


        return CartDetailResponse.builder().
                carts(carts).
                pagination(Pagination.builder().
                        currentPage(page).
                        totalPage((int)Math.ceil((double) totalItems/size)).
                        size(size).build()).build();
    }
}
