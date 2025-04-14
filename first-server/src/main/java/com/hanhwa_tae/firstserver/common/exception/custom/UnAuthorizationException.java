package com.hanhwa_tae.firstserver.common.exception.custom;

import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class UnAuthorizationException extends RuntimeException{
    private final ErrorCode errorCode;

    public UnAuthorizationException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
