package com.hanhwa_tae.secondserver.cart.command.application.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCartRequest {
    private final int count;

}
