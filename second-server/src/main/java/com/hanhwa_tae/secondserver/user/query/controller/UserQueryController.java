package com.hanhwa_tae.secondserver.user.query.controller;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.user.query.dto.response.*;
import com.hanhwa_tae.secondserver.user.query.service.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserQueryController {
    private final UserQueryService userQueryService;

    @Operation(summary = "회원 정보 조회", description = "회원은 자신의 정보를 조회할 수 있다.")
    @GetMapping("/info")
    public ResponseEntity<ApiResponse<UserInfoResponse>> getUserInfo(
            @AuthenticationPrincipal CustomUserDetail userDetail
            ){
        UserInfoResponse response = userQueryService.getUserInfo(userDetail);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "등급 목록 전체 조회", description = "모든 등급 목록을 조회할 수 있다.")
    @GetMapping("/ranks")
    public ResponseEntity<ApiResponse<RankInfoResponse>> getRankInfo(){
        RankInfoResponse response = userQueryService.getRankInfo();

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
