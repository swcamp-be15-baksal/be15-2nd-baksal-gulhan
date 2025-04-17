package com.hanhwa_tae.secondserver.utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.common.exception.ErrorResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtErrorResponse {
    private final ObjectMapper objectMapper;
    /* JWT 검증 중 발생한 에러 처리를 목적으로 함 */
    public void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ApiResponse<ErrorResponse> errorResponse = ApiResponse.failure(errorCode.getCode(), errorCode.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getHttpStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
