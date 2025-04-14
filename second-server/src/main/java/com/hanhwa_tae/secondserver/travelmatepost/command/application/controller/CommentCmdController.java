package com.hanhwa_tae.secondserver.travelmatepost.command.application.controller;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.CommentInsertRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.CommentUpdateRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.service.CommentCmdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "댓글")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentCmdController {

    private final CommentCmdService commentCmdService;

    /* 댓글 등록 */
    @Operation(summary = "댓글 등록", description = "해당 동행글에 댓글을 등록한다.")
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
    @Operation(summary = "댓글 수정", description = "해당 동행글에 본인에 댓글을 수정한다.")
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
    @Operation(summary = "댓글 삭제", description = "본인에 댓글을 삭제한다.")
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
