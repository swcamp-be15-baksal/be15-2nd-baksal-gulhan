package com.hanhwa_tae.gulhan.user.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.user.query.dto.request.LoginRequestUserRequest;
import com.hanhwa_tae.gulhan.user.query.dto.response.LoginRequestUserResponse;
import com.hanhwa_tae.gulhan.user.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserQueryController {
    private final UserQueryService userQueryService;

    @PostMapping("/loginRequest")
    public ResponseEntity<ApiResponse<LoginRequestUserResponse>> findLoginRequestUser(
            @RequestBody LoginRequestUserRequest request
    ){
        LoginRequestUserResponse response = userQueryService.findLoginRequestUser(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
