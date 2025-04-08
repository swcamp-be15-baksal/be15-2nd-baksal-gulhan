package com.hanhwa_tae.gulhan.cart.command.application.controller;


import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.application.service.CartCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartCommandController {
    private final CartCommandService cartCommandService;

    @PostMapping("/{userNo}")
    public int createMyCart(@PathVariable("userNo") int userNo, @RequestBody @Validated CreateCartRequest createCartRequest){
        int cartId = cartCommandService.registerCart(userNo, createCartRequest);
    }

}
