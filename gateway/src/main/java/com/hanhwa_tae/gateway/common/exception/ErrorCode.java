package com.hanhwa_tae.gateway.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ErrorCode {
    // 회원
    INVALID_LOGIN_REQUEST("10001", "아이디 또는 비밀번호가 잘못되었습니다.", HttpStatus.UNAUTHORIZED),  // 401
    REFRESH_TOKEN_EXPIRATION("10002", "로그인이 만료되었습니다.", HttpStatus.NOT_FOUND),  // 404
    ACCESS_DENIED("10003", "해당 페이지에 대한 조회 권한이 없습니다.", HttpStatus.FORBIDDEN),    // 403
    DUPLICATE_EMAIL_EXISTS("10004", "중복 이메일이 존재합니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_ID_EXISTS("10005", "중복 아이디가 존재합니다.", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("10006", "해당 유저를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    SAME_PASSWORD("10007", "이전 비밀번호와 동일합니다.", HttpStatus.BAD_REQUEST),
    PASSWORD_CONFIRM_FAILED("10008", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    USER_ALREADY_DELETED("10009", "이미 탈퇴 처리된 회원 입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_ADDITIONAL_INFO_EXISTS("10010", "이미 추가 정보를 입력한 회원 입니다.", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED("10011", "토큰이 만료되었습니다.", HttpStatus.BAD_REQUEST),

    // 그 외 기타 오류
    VALIDATION_ERROR("50000", "입력 값 검증 오류입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("50001", "내부 서버 오류 입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED_REQUEST("50002", "유효하지 않은 요청입니다.", HttpStatus.UNAUTHORIZED),
    SEND_EMAIL_FAILED("50003", "이메일 전송에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_CODE_EXPIRED("50004", "이메일 인증이 만료되었습니다.", HttpStatus.BAD_REQUEST);


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
