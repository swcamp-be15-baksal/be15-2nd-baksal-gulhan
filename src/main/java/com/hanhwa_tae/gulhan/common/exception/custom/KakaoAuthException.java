package com.hanhwa_tae.gulhan.common.exception.custom;

import com.hanhwa_tae.gulhan.common.exception.BusinessException;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;

public class KakaoAuthException extends BusinessException {

    public KakaoAuthException(ErrorCode errorCode) {
        super(errorCode);
    }

}
