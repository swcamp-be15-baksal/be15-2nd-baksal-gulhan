package com.hanhwa_tae.gulhan.travelmatepost.command.application.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.CommentInsertRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.CommentUpdateRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.service.CommentCmdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentCmdController {

    private final CommentCmdService commentCmdService;

    /* 댓글 등록 */
    @PostMapping("/{travelMatePostId}")
    public ResponseEntity<ApiResponse<Void>> insertComment(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable int travelMatePostId,
            @RequestBody CommentInsertRequest commentInsertRequest) {
        String id = customUserDetail.getUserId();
        commentCmdService.insertComment(id, travelMatePostId,commentInsertRequest);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 댓글 수정 */
    @PutMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> updateComment(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable int commentId,
            @RequestBody @Validated CommentUpdateRequest request) {
        String id = customUserDetail.getUserId();
        commentCmdService.updateComment(id, commentId,request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 댓글 삭제 */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable int commentId
    ) {
        String id = customUserDetail.getUserId();
        commentCmdService.deleteComment(id, commentId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
