package com.hanhwa_tae.secondserver.common.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private String code;
    private String message;
}
