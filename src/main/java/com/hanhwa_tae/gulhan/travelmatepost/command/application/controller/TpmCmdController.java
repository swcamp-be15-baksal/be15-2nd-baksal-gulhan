package com.hanhwa_tae.gulhan.travelmatepost.command.application.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.TmpUpdateRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.service.TmpCmdService;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.TmpInsertRequest;
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
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class TpmCmdController {

    private final TmpCmdService tmpCmdService;

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> createPost(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @RequestBody TmpInsertRequest request){
        String id = customUserDetail.getUserId();
        return ResponseEntity.ok(ApiResponse.success(tmpCmdService.createTmp(id, request)));
    }

    @PutMapping("/list/{travelMatePostId}")
    public ResponseEntity<ApiResponse<Void>> updatePost (
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Integer travelMatePostId,
            @RequestBody @Validated TmpUpdateRequest request
    ) {
        String id = customUserDetail.getUserId();
        tmpCmdService.updatePost(id, travelMatePostId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/list/{travelMatePostId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Integer travelMatePostId){
        String id = customUserDetail.getUserId();
        tmpCmdService.deletePost(id, travelMatePostId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
