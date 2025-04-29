package com.hanhwa_tae.secondserver.common.exception;

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
    ADDRESS_NOT_FOUND("10012", "주소를 찾을 수 없습니다.", HttpStatus.BAD_REQUEST),
    // 카카오 인증
    KAKAO_AUTH_FAILED("20001", "카카오 인증에 실패했습니다.", HttpStatus.UNAUTHORIZED),
    KAKAO_TOKEN_INVALID("20002", "카카오 토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED),
    KAKAO_TOKEN_EXPIRED("20003", "카카오 토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED),

    // JWT
    ACCESS_TOKEN_EXPIRED("11001", "Access Token이 만료되었습니다.", HttpStatus.UNAUTHORIZED),   // 401
    REFRESH_TOKEN_EXPIRED("11002", "Refresh Token이 만료되었습니다.", HttpStatus.UNAUTHORIZED), // 401
    INVALID_TOKEN("11003", "토큰이 유효하지 않습니다.", HttpStatus.BAD_REQUEST), // 400
    // TODO 나머지 이어서 적어주세요!

    // 장소 관련 오류
    PLACE_NOT_FOUND("12001", "해당 장소를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // 장바구니
    CART_NOT_FOUND("20001", "들어있지 않은 장바구니입니다.",HttpStatus.NOT_FOUND),// 404
    USER_NOT_MATCHING("20002", "해당 사용자는 삭제할 수 없습니다.", HttpStatus.UNAUTHORIZED),

    // package & goods & review
    PAGE_NOT_FOUND("20001", "삭제된 게시글입니다.", HttpStatus.NOT_FOUND),


    //동행글
    COMMENT_NOT_OWNED("30001","본인이 작성한 댓글이 아닙니다.", HttpStatus.BAD_REQUEST),
    COMMENT_NOT_FOUND("30002","댓글 정보를 찾을 수 없습니다.",HttpStatus.NOT_FOUND),
    POST_NOT_FOUND("30003","해당 번호에 게시글은 없습니다.",HttpStatus.NOT_FOUND),
    POST_NOT_OWNED("30004","본인이 작성한 게시글이 아닙니다.", HttpStatus.BAD_REQUEST),


    // 회원-마이페이지 조회 관련 오류
    ORDER_HISTORY_NOT_FOUND("30001", "구매 내역이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    DELIVERY_RESPONSE_ERROR("30002", "배송 정보 조회에 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    //공지사항
    NOTICE_NOT_FOUND("40001","존재하지않거나 삭제된 게시글 입니다.", HttpStatus.NOT_FOUND),
    ADMIN_NOT_MATCHING("40002", "해당 사용자는 관리자가 아닙니다.", HttpStatus.UNAUTHORIZED),

    // 그 외 기타 오류
    VALIDATION_ERROR("50000", "입력 값 검증 오류입니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("50001", "내부 서버 오류 입니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED_REQUEST("50002", "유효하지 않은 요청입니다.", HttpStatus.UNAUTHORIZED),
    SEND_EMAIL_FAILED("50003", "이메일 전송에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    EMAIL_CODE_EXPIRED("50004", "이메일 인증이 만료되었습니다.", HttpStatus.BAD_REQUEST),
    EMAIL_AUTHORIZATION_ERROR("50005", "이메일 인증에 실패하였습니다.",HttpStatus.BAD_REQUEST),
    IMAGE_FORMAT_ERROR("50006","지원하지 않는 이미지 형식 입니다.",HttpStatus.BAD_REQUEST),
    IMAGE_TOO_BIG("50007", "이미지 크기가 너무 큽니다.", HttpStatus.BAD_REQUEST),
    IMAGE_NOT_FOUND("50008", "이미지를 찾지 못하였습니다.", HttpStatus.NOT_FOUND);


    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
