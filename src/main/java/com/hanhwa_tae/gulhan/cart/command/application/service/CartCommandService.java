package com.hanhwa_tae.gulhan.cart.command.application.service;

import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.command.domain.repository.CartRepository;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartCommandService {
    private final CartRepository cartRepository;
    // 장바구니에 상품 등록
    @Transactional
    public int registerCart(CreateCartRequest createCartRequest){
        Cart cart = cartRepository.save(
                Cart.builder().
                        quantity(createCartRequest.getQuantity()).
                        targetType(createCartRequest.getTargetType()).
                        targetId(createCartRequest.getTargetId()).
                        build()
        );

        return cart.getCartItemId();
    }

    // 장바구니 상품 수정
    @Transactional
    public void updateCart(int cartId, UpdateCartRequest updateCartRequest){
        Cart cart = cartRepository.findById(cartId).
                orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));


        cart.updateCartCount(updateCartRequest.getCount());


    }
    // 장바구니 특정 상품 삭제
    @Transactional
    public void deleteCart(int cartId){
        cartRepository.deleteById(cartId);

    }

    // 장바구니 전체 삭제
    @Transactional
    public void deleteAllCart(int userId){
        cartRepository.deleteAllByUserId(userId);
    }
}
