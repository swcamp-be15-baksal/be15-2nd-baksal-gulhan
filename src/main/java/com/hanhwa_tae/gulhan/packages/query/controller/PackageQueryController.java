package com.hanhwa_tae.gulhan.packages.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.request.PackageSearchRequest;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageListResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageResponse;
import com.hanhwa_tae.gulhan.packages.query.service.PackageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/package")
@RequiredArgsConstructor
public class PackageQueryController {

    private final PackageQueryService packageQueryService;

    // 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<PackageListResponse>> getPackageList(PackageSearchRequest request) {
        PackageListResponse response = packageQueryService.getPackageList(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PackageResponse>> getPackageDetail(@PathVariable int id) {
        PackageResponse response = packageQueryService.getPackageDetail(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
