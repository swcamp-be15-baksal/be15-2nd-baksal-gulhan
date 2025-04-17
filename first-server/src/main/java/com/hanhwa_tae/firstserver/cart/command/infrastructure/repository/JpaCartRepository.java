package com.hanhwa_tae.firstserver.cart.command.infrastructure.repository;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.firstserver.cart.command.domain.repository.CartRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCartRepository extends CartRepository, JpaRepository<Cart, Integer>  {
}
