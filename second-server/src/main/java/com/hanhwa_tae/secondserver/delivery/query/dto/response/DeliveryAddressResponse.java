package com.hanhwa_tae.secondserver.delivery.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DeliveryAddressResponse {
   private List<DeliveryAddressDTO> addressList;
}
