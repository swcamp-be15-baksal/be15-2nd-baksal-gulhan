package com.hanhwa_tae.secondserver.common.exception.custom;

import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;

public class KakaoAuthException extends BusinessException {

    public KakaoAuthException(ErrorCode errorCode) {
        super(errorCode);
    }

}
