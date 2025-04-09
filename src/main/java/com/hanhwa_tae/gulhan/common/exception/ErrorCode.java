package com.hanhwa_tae.gulhan.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ErrorCode {
    // 회원
    USER_NOT_FOUND("10001", "아이디 또는 비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),  // 401
    REFRESH_TOKEN_EXPIRATION("10002", "로그인이 만료되었습니다.", HttpStatus.NOT_FOUND),  // 404
    ACCESS_DENIED("10003", "해당 페이지에 대한 조회 권한이 없습니다.", HttpStatus.FORBIDDEN),    // 403


    // 장바구니
    CART_NOT_FOUND("30001", "장바구니에 해당 품목이 없습니다.", HttpStatus.NOT_FOUND), // 404



    // 그 외 기타 오류
    VALIDATION_ERROR("50000", "입력 값 검증 오류입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("50001", "내부 서버 오류 입니다.", HttpStatus.INTERNAL_SERVER_ERROR);


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}