package com.hanhwa_tae.firstserver.payment.command.application.dto.request;

import com.hanhwa_tae.firstserver.common.domain.TargetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class CreateOrderHistoryRequest {
    private int cartId;
    private int price;
    private int quantity;
    private int targetId;
    private TargetType targetType;
    private String title;
}
