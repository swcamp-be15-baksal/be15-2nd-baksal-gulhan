package com.hanhwa_tae.secondserver.delivery.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryAddressDTO {
    private int userNo;
    private int deliveryAddressId;
    private String address;
    private String receiver;
    private String receiverPhone;
    private String defaultAddress;
    private String zipcode;
    private String detailAddress;
}
