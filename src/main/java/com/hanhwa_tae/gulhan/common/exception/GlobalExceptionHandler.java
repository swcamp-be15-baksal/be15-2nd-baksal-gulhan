package com.hanhwa_tae.gulhan.common.exception;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.common.exception.custom.PageNotFoundException;
import com.hanhwa_tae.gulhan.common.exception.custom.UnAuthorizationException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();

        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;
        StringBuilder errorMessage = new StringBuilder(errorCode.getMessage());
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(
                    String.format("[%s : %s]", error.getField(), error.getDefaultMessage()));
        }

        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorMessage.toString());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    // JWT 토큰 만료 시, refresh token 전송 요청을 보냄
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiResponse<Void>> handleExpiredJwtException(ExpiredJwtException e){
        ErrorCode errorCode = ErrorCode.ACCESS_TOKEN_EXPIRED;
        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(UnAuthorizationException.class)
    public ResponseEntity<ApiResponse<Void>> handleUnAuthorizationException(UnAuthorizationException e){
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED_REQUEST;

        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ApiResponse<Void>> handleMessagingException(MessagingException e){
        ErrorCode errorCode = ErrorCode.SEND_EMAIL_FAILED;
        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadCredentialsException(BadCredentialsException e){
        ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), e.getMessage());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException() {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handlePageNotFoundException() {
        ErrorCode errorCode = ErrorCode.PAGE_NOT_FOUND;

        ApiResponse<Void> response = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());

        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

}
