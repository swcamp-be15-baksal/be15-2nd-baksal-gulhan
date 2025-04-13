package com.hanhwa_tae.gulhan.like.command.application.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.like.command.application.dto.request.LikeCreateRequest;
import com.hanhwa_tae.gulhan.like.command.application.dto.response.LikeCommandResponse;
import com.hanhwa_tae.gulhan.like.command.application.service.LikeCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "관심등록")
@RestController
@RequestMapping("api/v1/like")
public class LikeCommandController {

    private final LikeCommandService likeCommandService;

    public LikeCommandController(LikeCommandService likeCommandService) {
        this.likeCommandService = likeCommandService;
    }

    @Operation(summary = "관심 등록", description = "관심있는 장소나 물품에 관심등록을 한다.")
    @PostMapping("/toggle")
    public ResponseEntity<?> toggleLike(
            @RequestBody LikeCreateRequest request,
            @AuthenticationPrincipal CustomUserDetail userDetails
    ) {
        String userId = userDetails.getUserId(); // JWT 토큰 기반 사용자 정보

        boolean liked = likeCommandService.toggleLike(userId, request);

        String message = liked ? "좋아요를 추가했습니다." : "좋아요를 취소했습니다.";
        LikeCommandResponse response = new LikeCommandResponse(
                liked,
                message,
                request.getTargetId(),
                request.getTargetType()
        );

        return ResponseEntity.ok(response);
    }
}


