package com.hanhwa_tae.secondserver.delivery.command.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryAddressRequest {
    private String address;
    private String receiver;
    private String receiverPhone;
}
