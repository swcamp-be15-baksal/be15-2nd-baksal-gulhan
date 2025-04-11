package com.hanhwa_tae.gulhan.travelmatepost.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.request.CommentSearchRequest;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.CommentListResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.service.CommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentQueryController {

    private  final CommentQueryService commentQueryService;

    @GetMapping("/{travelMatePostId}")
    public ResponseEntity<ApiResponse<CommentListResponse>> getComment(
            @PathVariable int travelMatePostId,
            CommentSearchRequest commentSearchRequest
    ) {

        CommentListResponse commentListResponse = commentQueryService.getComment(travelMatePostId,commentSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(commentListResponse));
    }
}
