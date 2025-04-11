package com.hanhwa_tae.gulhan.cart.command.domain.repository;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface CartRepository{
    Cart save(Cart cart);
    Optional<Cart> findById(Integer cartId);
    void deleteById(Integer cartId);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.userNo.userNo = :userNo")
    void deleteAllByUserNo(@Param("userNo") Long userNo);}
