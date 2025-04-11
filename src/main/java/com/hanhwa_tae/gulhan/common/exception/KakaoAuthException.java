package com.hanhwa_tae.gulhan.common.exception;

public class KakaoAuthException extends BusinessException{

    public KakaoAuthException() {
        super(ErrorCode.KAKAO_AUTH_FAILED);
    }

    public KakaoAuthException(ErrorCode errorCode) {
        super(errorCode);
    }

}
