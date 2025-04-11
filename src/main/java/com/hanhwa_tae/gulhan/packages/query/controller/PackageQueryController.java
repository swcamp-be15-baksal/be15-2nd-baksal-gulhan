package com.hanhwa_tae.gulhan.packages.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.request.PackageSearchRequest;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageListResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageDTO;
import com.hanhwa_tae.gulhan.packages.query.service.PackageQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackageQueryController {

    private final PackageQueryService packageQueryService;

    // 목록 조회
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PackageListResponse>> getPackages(@ModelAttribute PackageSearchRequest request) {
        PackageListResponse response = packageQueryService.getPackages(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 상세 조회
    @GetMapping("/list/{packageId}")
    public ResponseEntity<ApiResponse<PackageDTO>> getPackageDetail(@PathVariable Integer packageId) {
        PackageDTO response = packageQueryService.getPackageById(packageId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
