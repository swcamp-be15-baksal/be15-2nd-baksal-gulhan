package com.hanhwa_tae.firstserver.common.exception.custom;


import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;

public class KakaoAuthException extends BusinessException {

    public KakaoAuthException(ErrorCode errorCode) {
        super(errorCode);
    }

}
