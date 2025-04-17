package com.hanhwa_tae.firstserver.payment.command.application.controller;

import com.hanhwa_tae.firstserver.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.firstserver.cart.query.mapper.CartMapper;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.config.TossPaymentConfig;
import com.hanhwa_tae.firstserver.goods.query.mapper.GoodsMapper;
import com.hanhwa_tae.firstserver.packages.query.mapper.PackageMapper;
import com.hanhwa_tae.firstserver.payment.command.application.service.PaymentService;
import com.hanhwa_tae.firstserver.payment.command.domain.repository.GoodsPaymentRepository;
import com.hanhwa_tae.firstserver.payment.command.domain.repository.PackagesPaymentRepository;
import com.hanhwa_tae.firstserver.security.model.CustomUserDetail;
import com.hanhwa_tae.firstserver.user.query.dto.response.UserResponse;
import com.hanhwa_tae.firstserver.user.query.mapper.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/payment")
@RequiredArgsConstructor
public class GetPayInfoController {
    private final TossPaymentConfig tossPaymentConfig;
    private final CartMapper cartMapper;
    private final GoodsPaymentRepository goodsPaymentRepository;
    private final PackagesPaymentRepository packagesPaymentRepository;
    private final PaymentService paymentService;
    private final UserMapper userMapper;
    private final PackageMapper packageMapper;
    private final GoodsMapper goodsMapper;

    /*토스로부터 키값 받아오기*/
    @Operation(summary = "토스 키 가져오기(프론트에서)", description = "토스 인증용 코드 보내기")
    @GetMapping("/key")
    public Map<String, String> getKeyValue() {
        Map<String, String> result = new HashMap<>();
        String value = tossPaymentConfig.getTestClientApiKey();
        result.put("keyValue", value);
        return result;
    }
    /*토스로 전송할 값 추출*/
    @Operation(summary = "주문 ID, 장바구니의 모든 값 가져오기(프론트에서)", description = "결제에 필요한 값 보내줍니다.")
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
    // 결제 후 정보 제공
    @Operation(summary = "결제에 필요한 정보 보내기", description = "결제에 필요한 개수 등을 보내준다.")
    @GetMapping("/payinfo")
    public Map<String, Object> getPayInfo(@AuthenticationPrincipal CustomUserDetail userDetail){
        Long userNo = userDetail.getUserNo();
        List<CartResponse> carts = cartMapper.selectAllCartByUserNo(userNo);
        UserResponse UserInfo = userMapper.findUserInfoByUserId(userDetail.getUserId()).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );
        String name = "" ;
        String username = UserInfo.getUsername();
        String email = UserInfo.getEmail();
        int cnt = -1;
        for(CartResponse cart : carts){
            if (cnt < 0){
                if(cart.getTargetType().name().equalsIgnoreCase("Goods")) {
                    name = goodsMapper.selectGoodsNameById(cart.getTargetId());
                    cnt += 1;

                }else if(cart.getTargetType().name().equalsIgnoreCase("Packages")){
                    name = packageMapper.selectPackageNameById(cart.getTargetId());
                    cnt += 1;
                }

            }
            else{
                cnt += 1;
            }
        }
        String orderName = "";
        if(cnt < 0){
            orderName = name;

        }else{
            orderName = name + " 외 " + cnt + "건";
        }

        Map<String, Object> result = new HashMap<>();
        result.put("name", username);
        result.put("email", email);
        result.put("ordername", orderName);


        return result;

    }
}
