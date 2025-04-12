package com.hanhwa_tae.gulhan.payment.command.application.controller;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.cart.command.domain.aggregate.Cart;
import com.hanhwa_tae.gulhan.cart.query.dto.response.CartResponse;
import com.hanhwa_tae.gulhan.cart.query.mapper.CartMapper;
import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import com.hanhwa_tae.gulhan.goods.query.mapper.GoodsMapper;
import com.hanhwa_tae.gulhan.packages.query.mapper.PackageMapper;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@RestController
public class PayInfoController {

    private final CartMapper cartMapper;
    private final UserMapper userMapper;
    private final PackageMapper packageMapper;
    private final GoodsMapper goodsMapper;

    @GetMapping("/payinfo")
    public Map<String, Object> getPayInfo(@AuthenticationPrincipal CustomUserDetail userDetail){
        Long userNo = userDetail.getUserNo();
        List<CartResponse> carts = cartMapper.selectAllCartByUserNo(userNo);
        User user = userMapper.findUserByUserId(userDetail.getUserId()).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );
        String name = "" ;
        String username = user.getUsername();
        String email = user.getEmail();
        int cnt = -1;
        for(CartResponse cart : carts){
            if (cnt < 0){
                if(cart.getTargetType().name().equalsIgnoreCase("Goods")) {
                    name = goodsMapper.selectGoodsNameById(cart.getTargetId());
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
