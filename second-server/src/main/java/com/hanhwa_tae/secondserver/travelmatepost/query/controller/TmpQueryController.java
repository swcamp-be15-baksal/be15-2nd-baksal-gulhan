package com.hanhwa_tae.secondserver.travelmatepost.query.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.request.TmpSearchRequest;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpDetailResponse;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpListResponse;
import com.hanhwa_tae.secondserver.travelmatepost.query.service.TmpQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "동행글")
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class TmpQueryController {

    private final TmpQueryService tmpQueryService;

    @Operation(summary = "동행글 목록 조회", description = "등록된 동행글 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<TmpListResponse>> TravelMatePostList(TmpSearchRequest request) {

        TmpListResponse res = tmpQueryService.getTmpList(request);

        return ResponseEntity.ok(ApiResponse.success(res));
    }

    @Operation(summary = "동행글 상세 조회", description = "등록된 동행글을 상세 조회한다.")
    @GetMapping("/list/{travelMatePostId}")
    public ResponseEntity<ApiResponse<TmpDetailResponse>> getTmpList(@PathVariable int travelMatePostId) {
            TmpDetailResponse response = tmpQueryService.getTmpList(travelMatePostId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
