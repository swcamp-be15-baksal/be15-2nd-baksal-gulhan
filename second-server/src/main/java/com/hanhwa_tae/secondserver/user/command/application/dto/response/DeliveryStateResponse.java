package com.hanhwa_tae.secondserver.user.command.application.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeliveryStateResponse {
    private final String state;
    private final String time;
}
