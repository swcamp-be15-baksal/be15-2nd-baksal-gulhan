package com.hanhwa_tae.secondserver.delivery.query.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DeliveryInfoRequest {
    private final String carrierId;
    private final String trackingNumber;
}
