package com.hanhwa_tae.secondserver.delivery.command.application.dto.request;

import com.hanhwa_tae.secondserver.delivery.command.domain.aggregate.DefaultAddress;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryAddressRequest {
    @NotNull
    private String address;
    @NotNull
    private String receiver;
    @NotNull
    private String receiverPhone;
    @NotNull
    @Pattern(regexp = "Y|N")
    private DefaultAddress defaultAddress;
}
