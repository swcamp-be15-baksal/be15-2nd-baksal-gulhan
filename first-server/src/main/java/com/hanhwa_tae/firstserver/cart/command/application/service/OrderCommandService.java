package com.hanhwa_tae.firstserver.cart.command.application.service;

import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Confirm;
import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.Order;
import com.hanhwa_tae.firstserver.cart.command.domain.aggregate.OrderHistory;
import com.hanhwa_tae.firstserver.cart.command.domain.repository.OrderHistoryRepository;
import com.hanhwa_tae.firstserver.cart.command.domain.repository.OrderRepository;
import com.hanhwa_tae.firstserver.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.firstserver.cart.query.dto.response.OrderHistoryResponse;
import com.hanhwa_tae.firstserver.cart.query.mapper.CartMapper;
import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.goods.command.domain.repository.JpaGoodsRepository;
import com.hanhwa_tae.firstserver.packages.command.domain.repository.JpaPackageRepository;
import com.hanhwa_tae.firstserver.payment.command.application.dto.request.CreateOrderRequest;
import com.hanhwa_tae.firstserver.review.query.mapper.OrderMapper;
import com.hanhwa_tae.firstserver.user.query.dto.response.UserDetailResponse;
import com.hanhwa_tae.firstserver.payment.query.mapper.PaymentMapper;
import com.hanhwa_tae.firstserver.user.query.mapper.UserMapper;
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
    private final OrderMapper orderMapper;
    private final JpaGoodsRepository jpaGoodsRepository;
    private final JpaPackageRepository jpaPackageRepository;

    public String updateGoodsCountOfTargetId(int quantity, int targetId) {

        jpaGoodsRepository.decreaseGoodsQuantity(quantity,targetId);

        return "ok";
    }
    public String updatePackagesCountOfTargetId(int quantity, int targetId){
        jpaPackageRepository.decreasePackagesQuantity(quantity,targetId);
        return "ok";
    }

    public String registerOrder(String userId, CreateOrderRequest createOrderRequest, int point){
        Long userNo = userMapper.findUserNoByUserId(userId).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        UserDetailResponse userinfo = userMapper.findUserInfoDetailByUserId(userId).
                orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        List<CartResponse> cartList = cartMapper.selectAllCartByUserNo(userNo);
        int totalAmount = cartList.stream().
                mapToInt(amount -> amount.getQuantity()).
                sum();
        int totalPoint = userinfo.getPoint() + point;
        Order order = orderRepository.
                save(
                        Order.builder().
                                orderId(createOrderRequest.getOrderId()).
                                orderedAt(new Date()).
                                totalPrice(createOrderRequest.getTotalPrice()).
                                totalPoint(totalPoint).
                                totalAmount(totalAmount).
                                address(userinfo.getAddress()).
                                receiver(userMapper.findUserInfoByUserId(userId).orElseThrow(
                                        () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
                                ).getUsername()).
                                receiverPhone(userinfo.getPhone()).
                                orderCode(createOrderRequest.getOrderCode()).
                                userNo(userNo).
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

    public String updateItemCount(Long usenNo) {
        String orderId = orderMapper.selectOrderIdByUserNo(usenNo);
        List<OrderHistoryResponse> orderHistories = orderMapper.
                selectOrderInfoByOrderId(orderId);
        String result = "";
        for(OrderHistoryResponse orderHistory : orderHistories){
            if(orderHistory.getOrderHistoryType().name().equalsIgnoreCase("Goods")){
                result = jpaGoodsRepository.increaseGoodsQuantity(orderHistory.getQuantity(),orderHistory.getTargetId());
            }
            else{
                result = jpaPackageRepository.increasePackagesQuantity(orderHistory.getQuantity(), orderHistory.getTargetId());

            }
        }

        return "ok";
    }
}
