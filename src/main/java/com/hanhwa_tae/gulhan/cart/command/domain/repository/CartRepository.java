package com.hanhwa_tae.gulhan.cart.command.domain.repository;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;


import java.util.Optional;

public interface CartRepository{
    Cart save(Cart cart);
    Optional<Cart> findById(Integer cartId);
    void deleteById(Integer cartId);

    void deleteAllByUserId(int userId);
}
