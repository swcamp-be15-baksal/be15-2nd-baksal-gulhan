package com.hanhwa_tae.gulhan.payment.command.application.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CancelRequestDto {
    private final String cancelReason;
}
