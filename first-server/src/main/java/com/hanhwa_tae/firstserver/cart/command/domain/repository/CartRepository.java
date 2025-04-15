package com.hanhwa_tae.firstserver.cart.command.domain.repository;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CartRepository{
    Cart save(Cart cart);
    Optional<Cart> findById(Integer cartId);
    void deleteById(Integer cartId);

    @Modifying
    @Query("DELETE FROM Cart c WHERE c.userNo = :userNo")
    void deleteAllByUserNo(@Param("userNo") Long userNo);


    @Modifying
    @Query("UPDATE Cart c SET c.quantity = c.quantity + :quantity " +
            "WHERE c.targetType = :targetType " +
            "AND c.targetId = :targetId " +
            "AND c.userNo = :userNo")
    int updateCartQuantity(int quantity, TargetType targetType, int targetId, Long userNo);

    Optional<Cart> findByTargetTypeAndTargetIdAndUserNo(TargetType targetType, int targetId, Long userNo);



}
