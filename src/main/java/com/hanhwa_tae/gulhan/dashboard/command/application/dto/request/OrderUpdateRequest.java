package com.hanhwa_tae.gulhan.dashboard.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderUpdateRequest {

    @NotBlank(message = "운송장 번호입력해주세요.")
    private String shippingNo;

    private String address;

    private String receiver;

    private String receiverPhone;

    private String orderCode;
}