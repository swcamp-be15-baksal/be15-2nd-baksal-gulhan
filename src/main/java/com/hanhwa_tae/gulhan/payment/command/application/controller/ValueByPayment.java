package com.hanhwa_tae.gulhan.payment.command.application.controller;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.gulhan.cart.query.mapper.CartMapper;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.payment.command.application.service.PaymentService;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class ValueByPayment {
    private final CartMapper cartMapper;
    private final GoodsPaymentRepository goodsPaymentRepository;
    private final PackagesPaymentRepository packagesPaymentRepository;
    private final PaymentService paymentService;

    @GetMapping("/value")
    public Map<String, Object> getEnvValue(@AuthenticationPrincipal CustomUserDetail userDetail) {
        Long userNo = userDetail.getUserNo();


        List<CartResponse> carts = cartMapper.selectAllCartByUserNo(userNo);
        int value = 0;


        for(CartResponse cartResponse:carts){
            int productId = cartResponse.getTargetId();
            int quantity = cartResponse.getQuantity();
            TargetType targetType = cartResponse.getTargetType();

            if(targetType.name().equalsIgnoreCase("Goods")){
                value += goodsPaymentRepository.findPriceById(productId) * quantity;

            }else if(targetType.name().equalsIgnoreCase("Packages")){
                value += packagesPaymentRepository.findPriceById(productId) * quantity;

            }
        }
        String orderId = UUID.randomUUID().toString();

        Map<String, Object> result = new HashMap<>();
        result.put("value", value);
        result.put("orderId", orderId);

        paymentService.savePayment(value, orderId);
        return result;
    }
}
