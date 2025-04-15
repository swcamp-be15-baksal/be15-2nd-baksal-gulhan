package com.hanhwa_tae.secondserver.cart.command.application.service;

import com.hanhwa_tae.secondserver.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.secondserver.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.secondserver.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.secondserver.cart.command.domain.repository.CartRepository;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartCommandService {
    private final CartRepository cartRepository;
    private final UserMapper userMapper;
    // 장바구니에 상품 등록
    @Transactional
    public int registerCart(String id, CreateCartRequest createCartRequest){

        User user = userMapper.findUserByUserId(id).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        Cart cart = cartRepository.save(
                Cart.builder().
                        quantity(createCartRequest.getQuantity()).
                        targetType(createCartRequest.getTargetType()).
                        targetId(createCartRequest.getTargetId()).
                        userNo(user).
                        build()
        );

        return cart.getCartItemId();
    }

    // 장바구니 상품 수정
    @Transactional
    public void updateCart(String id, int cartId, UpdateCartRequest updateCartRequest){
        Long userNo = userMapper.findUserByUserId(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)).getUserNo();
        Cart cart = cartRepository.findById(cartId).
                orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));
        if(!cart.getUserNo().getUserNo().equals(userNo)){
            throw new BusinessException(ErrorCode.USER_NOT_MATCHING);
        }

        cart.updateCartCount(updateCartRequest.getCount());
    }
    // 장바구니 특정 상품 삭제
    @Transactional
    public void deleteCart(String id, int cartId){
        Long userNo = userMapper.findUserByUserId(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)).getUserNo();

        Cart cart = cartRepository.findById(cartId).
                orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));
        if(!cart.getUserNo().getUserNo().equals(userNo)){
            throw new BusinessException(ErrorCode.USER_NOT_MATCHING);
        }

        cartRepository.deleteById(cartId);

    }

    // 장바구니 전체 삭제
    @Transactional
    public void deleteAllCart(String userId){
        Long userNo = userMapper.findUserByUserId(userId).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND)).getUserNo();
        cartRepository.deleteAllByUserNo(userNo);
    }
}
