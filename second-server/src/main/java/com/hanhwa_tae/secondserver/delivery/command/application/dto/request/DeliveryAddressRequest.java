package com.hanhwa_tae.secondserver.delivery.command.application.dto.request;

import com.hanhwa_tae.secondserver.delivery.command.domain.aggregate.DefaultAddress;
import com.hanhwa_tae.secondserver.user.annotation.ValidPhone;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryAddressRequest {
    @NotNull
    private String zipcode;
    @NotNull
    private String address;
    @NotNull
    private String detailAddress;
    @NotNull
    private String receiver;
    @NotNull
    @ValidPhone
    private String receiverPhone;
    @NotNull
    private DefaultAddress defaultAddress;
}
