package com.hanhwa_tae.gulhan.user.query.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.*;
import com.hanhwa_tae.gulhan.user.query.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserQueryController {
    private final UserQueryService userQueryService;

    @GetMapping("/info")
    public ResponseEntity<ApiResponse<UserInfoResponse>> getUserInfo(
            @AuthenticationPrincipal CustomUserDetail userDetail
            ){
        UserInfoResponse response = userQueryService.getUserInfo(userDetail);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/mypage/review")
    public ResponseEntity<ApiResponse<UserReviewResponse>> getUserReview(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestParam TargetType targetType
    ){
        UserReviewResponse response = userQueryService.getUserReview(userDetail, targetType);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/ranks")
    public ResponseEntity<ApiResponse<RankInfoResponse>> getRankInfo(){
        RankInfoResponse response = userQueryService.getRankInfo();

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/mypage/comment")
    public ResponseEntity<ApiResponse<UserCommentResponse>> getUserComment(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        UserCommentResponse response = userQueryService.getUserComment(userDetail);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
