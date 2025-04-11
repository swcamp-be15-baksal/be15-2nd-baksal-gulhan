package com.hanhwa_tae.gulhan.payment.command.application.controller;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.command.domain.repository.CartRepository;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.gulhan.cart.query.mapper.CartMapper;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.payment.command.domain.repository.GoodsPaymentRepository;
import com.hanhwa_tae.gulhan.payment.command.domain.repository.PackagesPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class SumValueByPayment {
    private final CartMapper cartMapper;
    private final GoodsPaymentRepository goodsPaymentRepository;
    private final PackagesPaymentRepository packagesPaymentRepository;

    @GetMapping("/value")
    public Map<String, Integer> getEnvValue(@AuthenticationPrincipal CustomUserDetail userDetail) {
        Long userNo = userDetail.getUserNo();


        List<CartResponse> carts = cartMapper.selectAllCartByUserNo(userNo);
        int value = 0;


        for(CartResponse cartResponse:carts){
            int productId = cartResponse.getCartId();
            int quantity = cartResponse.getQuantity();
            TargetType targetType = cartResponse.getTargetType();

            if(targetType.name().equalsIgnoreCase("Goods")){
                value += goodsPaymentRepository.findPriceById(productId) * quantity;

            }else if(targetType.name().equalsIgnoreCase("Packages")){
                value += packagesPaymentRepository.findPriceById(productId) * quantity;

            }
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("value", value);
        return result;
    }
}
