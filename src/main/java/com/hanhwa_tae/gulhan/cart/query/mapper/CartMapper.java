package com.hanhwa_tae.gulhan.cart.query.mapper;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.query.dto.request.CartSearchRequest;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartResponse> selectCartsByUserId(int userId, CartSearchRequest cartSearchRequest);
    long countCarts(int userId);

}
