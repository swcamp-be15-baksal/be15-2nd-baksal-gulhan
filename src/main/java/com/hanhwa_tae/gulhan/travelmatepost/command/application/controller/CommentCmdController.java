package com.hanhwa_tae.gulhan.travelmatepost.command.application.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.CommentInsertRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.CommentUpdateRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.response.CommentCmdResponse;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.service.CommentCmdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment/board")
@RequiredArgsConstructor
public class CommentCmdController {

    private final CommentCmdService commentCmdService;

    /* 댓글 등록 */
    @PostMapping("/{travelMatePostId}")
    public ResponseEntity<ApiResponse<Void>> insertComment(@PathVariable int travelMatePostId, @RequestBody CommentInsertRequest commentInsertRequest) {
        commentCmdService.insertComment(travelMatePostId,commentInsertRequest);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 댓글 수정 */
    @PutMapping
    public ResponseEntity<ApiResponse<Void>> updateComment(@RequestBody CommentUpdateRequest request) {
        commentCmdService.updateComment(request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 댓글 삭제 */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(@PathVariable int commentId) {
        commentCmdService.deleteComment(commentId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
