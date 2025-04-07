package com.hanhwa_tae.gulhan.travelmatepost.command.application.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.TmpUpdateRequest;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.service.TmpCmdService;
import com.hanhwa_tae.gulhan.travelmatepost.command.application.dto.request.TmpInsertRequest;
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
@RequestMapping("/board")
@RequiredArgsConstructor
public class TpmCmdController {

    private final TmpCmdService tmpCmdService;

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> createPost(@RequestBody TmpInsertRequest request){
        return ResponseEntity.ok(ApiResponse.success(tmpCmdService.createTmp(request)));
    }

    @PutMapping("/list/{travelMatePostId}")
    public ResponseEntity<ApiResponse<Void>> updatePost (@PathVariable Integer travelMatePostId, @RequestBody TmpUpdateRequest request) {
        tmpCmdService.updatePost(travelMatePostId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/list/{travelMatePostId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Integer travelMatePostId){
        tmpCmdService.deletePost(travelMatePostId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
