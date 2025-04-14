package com.hanhwa_tae.gulhan.cart.command.application.service;

import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.application.dto.request.UpdateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.command.domain.repository.CartRepository;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.gulhan.cart.query.mapper.CartMapper;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyStore;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartCommandService {
    private final CartRepository cartRepository;
    private final UserMapper userMapper;
    private final CartMapper cartMapper;

    // 장바구니에 상품 등록
    @Transactional
    public String registerCart(String id, CreateCartRequest createCartRequest){

        User user = userMapper.findUserByUserId(id).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        int isExisted = cartRepository.updateCartQuantity(createCartRequest.getQuantity(),
                createCartRequest.getTargetType(),
                createCartRequest.getTargetId(),
                user.getUserNo());
        if(isExisted > 0){

            return cartRepository.findByTargetTypeAndTargetIdAndUserNo(createCartRequest.getTargetType(),
                    createCartRequest.getTargetId(),
                    user).orElseThrow(() -> new BusinessException(ErrorCode.CART_NOT_FOUND)).getCartItemId() + "번 장바구니가 수정됐습니다.";

        }
        else{
            Cart newCart = cartRepository.save(
                    Cart.builder().
                            quantity(createCartRequest.getQuantity()).
                            targetType(createCartRequest.getTargetType()).
                            targetId(createCartRequest.getTargetId()).
                            userNo(user).
                            build()

            );
            return newCart.getCartItemId() + "번 장바구니가 생성됐습니다.";

        }

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
