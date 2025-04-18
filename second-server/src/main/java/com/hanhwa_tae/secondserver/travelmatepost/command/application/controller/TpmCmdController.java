package com.hanhwa_tae.secondserver.travelmatepost.command.application.controller;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.TmpInsertRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.TmpUpdateRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.service.TmpCmdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "동행글")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class TpmCmdController {

    private final TmpCmdService tmpCmdService;

    @Operation(summary = "동행글 작성", description = "동행글을 작성한다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> createPost(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @RequestBody TmpInsertRequest request){
        String id = customUserDetail.getUserId();
        return ResponseEntity.ok(ApiResponse.success(tmpCmdService.createTmp(id, request)));
    }

    @Operation(summary = "동행글 수정", description = "본인이 작성한 동행글을 수정한다.")
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

    @Operation(summary = "동행글 삭제", description = "본인이 작성한 동행글을 삭제한다.")
    @DeleteMapping("/list/{travelMatePostId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Integer travelMatePostId){
        String id = customUserDetail.getUserId();
        tmpCmdService.deletePost(id, travelMatePostId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
