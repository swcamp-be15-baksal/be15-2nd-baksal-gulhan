package com.hanhwa_tae.gulhan.travelmatepost.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.request.TmpSearchRequest;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpDetailResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpListResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.service.TmpQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class TmpQueryController {

    private final TmpQueryService tmpQueryService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<TmpListResponse>> TravelMatePostList(TmpSearchRequest request) {

        TmpListResponse res = tmpQueryService.getTmpList(request);

        return ResponseEntity.ok(ApiResponse.success(res));
    }

    @GetMapping("/list/{travelMatePostId}")
    public ResponseEntity<ApiResponse<TmpDetailResponse>> getTmpList(@PathVariable int travelMatePostId) {
            TmpDetailResponse response = tmpQueryService.getTmpList(travelMatePostId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }



}
