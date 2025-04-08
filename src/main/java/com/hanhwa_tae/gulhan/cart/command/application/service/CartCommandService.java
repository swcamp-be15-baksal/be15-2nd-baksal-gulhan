package com.hanhwa_tae.gulhan.cart.command.application.service;

import com.hanhwa_tae.gulhan.cart.command.application.dto.request.CreateCartRequest;
import com.hanhwa_tae.gulhan.cart.command.domain.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartCommandService {
    private final CartRepository cartRepository;

    public int registerCart(int userNo, CreateCartRequest createCartRequest){
        return 1;
    }

}
