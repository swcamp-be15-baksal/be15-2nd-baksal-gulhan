package com.hanhwa_tae.secondserver.cart.query.mapper;

import com.hanhwa_tae.secondserver.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.secondserver.cart.query.dto.response.CartResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    List<CartResponse> selectCartsByUserNo(Long userNo, CartSearchRequest cartSearchRequest);
    long countCarts(Long userNo);

}
