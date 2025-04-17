package com.hanhwa_tae.firstserver.payment.command.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CancelRequestDto {
    private final String cancelReason;
}
