package com.hanhwa_tae.gulhan.like.query.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.like.query.dto.request.LikeSearchRequest;
import com.hanhwa_tae.gulhan.like.query.dto.response.LikeListResponse;
import com.hanhwa_tae.gulhan.like.query.service.LikeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관심등록")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/like")
public class LikeQueryController {
    private final LikeQueryService likeQueryService;

    @Operation(summary = "본인 관심목록 조회", description = "본인 관심목록을 조회한다.")
    @GetMapping("/likes")
    public ResponseEntity<ApiResponse<LikeListResponse>> getLikes(
            @AuthenticationPrincipal CustomUserDetail userDetails,
            LikeSearchRequest likeSearchRequest
    ) {
        String userId = userDetails.getUserId();
        LikeListResponse response = likeQueryService.getLikes(userId, likeSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
