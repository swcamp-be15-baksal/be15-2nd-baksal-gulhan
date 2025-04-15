package com.hanhwa_tae.secondserver.cart.command.infrastructure.repository;

import com.hanhwa_tae.secondserver.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.secondserver.cart.command.domain.repository.CartRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCartRepository extends CartRepository, JpaRepository<Cart, Integer>  {
}
