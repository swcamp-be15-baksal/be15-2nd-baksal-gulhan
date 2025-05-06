package com.hanhwa_tae.secondserver.delivery.query.dto.response;

import com.hanhwa_tae.secondserver.delivery.command.domain.aggregate.DefaultAddress;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryAddressDTO {
    private int userNo;
    private String address;
    private String receiver;
    private String receiverPhone;
    private DefaultAddress defaultAddress;
    private String zipcode;
    private String detailAddress;
}
