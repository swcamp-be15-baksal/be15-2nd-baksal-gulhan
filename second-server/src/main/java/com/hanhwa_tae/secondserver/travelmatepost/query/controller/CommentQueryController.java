package com.hanhwa_tae.secondserver.travelmatepost.query.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.request.CommentSearchRequest;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.CommentListResponse;
import com.hanhwa_tae.secondserver.travelmatepost.query.service.CommentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "댓글")
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentQueryController {

    private  final CommentQueryService commentQueryService;

    @Operation(summary = "댓글 조회", description = "해당 동행글에 등록된 댓글을 조회한다.")
    @GetMapping("/{travelMatePostId}")
    public ResponseEntity<ApiResponse<CommentListResponse>> getComment(
            @PathVariable int travelMatePostId,
            CommentSearchRequest commentSearchRequest
    ) {

        CommentListResponse commentListResponse = commentQueryService.getComment(travelMatePostId,commentSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(commentListResponse));
    }
}
