package com.hanhwa_tae.secondserver.dashboard.command.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateRequest {

    private String shippingNo;

    private String address;

    private String receiver;

    private String receiverPhone;

}