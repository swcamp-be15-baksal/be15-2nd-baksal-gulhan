package com.hanhwa_tae.gulhan.cart.command.application.service;

import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Confirm;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.OrderHistory;
import com.hanhwa_tae.gulhan.cart.command.domain.repository.OrderHistoryRepository;
import com.hanhwa_tae.gulhan.cart.command.domain.repository.OrderRepository;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.gulhan.cart.query.mapper.CartMapper;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.payment.command.application.dto.request.CreateOrderRequest;
import com.hanhwa_tae.gulhan.payment.query.mapper.PaymentMapper;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.UserInfo;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserInfoResponse;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderCommandService {
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderRepository orderRepository;
    private final UserMapper userMapper;
    private final PaymentMapper paymentMapper;
    private final CartMapper cartMapper;

    public String registerOrder(String userId, CreateOrderRequest createOrderRequest, int point){
        User user = userMapper.findUserByUserId(userId).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        UserInfoResponse userinfo = userMapper.findUserInfoByUserId(userId).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        List<CartResponse> cartList = cartMapper.selectAllCartByUserNo(user.getUserNo());
        int totalAmount = cartList.stream().
                mapToInt(amount -> amount.getQuantity()).
                sum();
        int totalPoint = userMapper.findPointByUserNo(user.getUserNo()) + point;
        Order order = orderRepository.
                save(
                        Order.builder().
                                orderId(createOrderRequest.getOrderId()).
                                orderedAt(new Date()).
                                totalPrice(createOrderRequest.getTotalPrice()).
                                totalPoint(totalPoint).
                                totalAmount(totalAmount).
                                address(userinfo.getAddress()).
                                receiver(userinfo.getUsername()).
                                receiverPhone(userinfo.getPhone()).
                                orderCode(createOrderRequest.getOrderCode()).
                                user(user).
                                isConfirmed(Confirm.N)
                                .build()
                );

        return "ok";
    }


    public String registerOrderHistory(Long userNo, List<CartResponse> cartList) {
        Order order = paymentMapper.findOrderByUserNo(userNo).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        for(CartResponse cart : cartList){
            orderHistoryRepository.save(
                    OrderHistory.builder().
                            targetType(cart.getTargetType()).
                            targetId(cart.getTargetId()).
                            quantity(cart.getQuantity()).
                            orderId(order).build());
        }

        return "ok";


    }

    public Order searchPaymentKeyByUserNo(Long userNo) {
        Order paymentKey = paymentMapper.findOrderByUserNo(userNo).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return paymentKey;
    }

}
