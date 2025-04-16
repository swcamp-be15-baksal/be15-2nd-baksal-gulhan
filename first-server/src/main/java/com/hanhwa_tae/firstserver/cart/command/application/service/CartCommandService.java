package com.hanhwa_tae.firstserver.cart.command.application.service;

import com.hanhwa_tae.firstserver.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.firstserver.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.firstserver.cart.command.domain.repository.CartRepository;
import com.hanhwa_tae.firstserver.cart.query.mapper.CartMapper;
import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartCommandService {
    private final CartRepository cartRepository;
    private final UserMapper userMapper;
    private final CartMapper cartMapper;

    // 장바구니에 상품 등록
    @Transactional
    public String registerCart(String id, CreateCartRequest createCartRequest){

        Long userNo = userMapper.findUserNoByUserId(id).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        int isExisted = cartRepository.updateCartQuantity(createCartRequest.getQuantity(),
                createCartRequest.getTargetType(),
                createCartRequest.getTargetId(),
                userNo);
        if(isExisted > 0){

            return cartRepository.findByTargetTypeAndTargetIdAndUserNo(createCartRequest.getTargetType(),
                    createCartRequest.getTargetId(),
                    userNo).orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND)).getCartItemId() + "번 장바구니가 수정됐습니다.";

        }
        else{
            Cart newCart = cartRepository.save(
                    Cart.builder().
                            quantity(createCartRequest.getQuantity()).
                            targetType(createCartRequest.getTargetType()).
                            targetId(createCartRequest.getTargetId()).
                            userNo(userNo).
                            build()

            );
            return newCart.getCartItemId() + "번 장바구니가 생성됐습니다.";

        }

    }

    // 장바구니 상품 수정
    @Transactional
    public void updateCart(String id, int cartId, UpdateCartRequest updateCartRequest){
        Long userNo = userMapper.findUserNoByUserId(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        Cart cart = cartRepository.findById(cartId).
                orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));
        if(!cart.getUserNo().equals(userNo)){
            throw new BusinessException(ErrorCode.USER_NOT_MATCHING);
        }

        cart.updateCartCount(updateCartRequest.getCount());
    }
    // 장바구니 특정 상품 삭제
    @Transactional
    public void deleteCart(String id, int cartId){
        Long userNo = userMapper.findUserNoByUserId(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        Cart cart = cartRepository.findById(cartId).
                orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND));
        if(!cart.getUserNo().equals(userNo)){
            throw new BusinessException(ErrorCode.USER_NOT_MATCHING);
        }

        cartRepository.deleteById(cartId);

    }

    // 장바구니 전체 삭제
    @Transactional
    public void deleteAllCart(String userId){
        Long userNo = userMapper.findUserNoByUserId(userId).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        cartRepository.deleteAllByUserNo(userNo);
    }
}
