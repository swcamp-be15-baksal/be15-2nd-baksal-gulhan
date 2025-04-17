package com.hanhwa_tae.gulhan.user.command.application.dto.request;

import lombok.Getter;

@Getter
public class DeliveryAddressRequest {
    private String address;
    private String receiver;
    private String receiverPhone;
}
