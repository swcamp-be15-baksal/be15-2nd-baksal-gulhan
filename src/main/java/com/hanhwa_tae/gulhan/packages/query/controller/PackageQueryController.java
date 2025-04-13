package com.hanhwa_tae.gulhan.packages.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.request.PackageSearchRequest;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageListResponse;
import com.hanhwa_tae.gulhan.packages.query.dto.response.PackageDTO;
import com.hanhwa_tae.gulhan.packages.query.service.PackageQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "패키지")
@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackageQueryController {

    private final PackageQueryService packageQueryService;

    // 목록 조회
    @Operation(summary = "패키지 목록 조회", description = "패키지를 목록 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<PackageListResponse>> getPackages(@ModelAttribute PackageSearchRequest request) {
        PackageListResponse response = packageQueryService.getPackages(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    // 상세 조회
    @Operation(summary = "패키지 상세 조회", description = "패키지를 상세 조회한다.")
    @GetMapping("/list/{packageId}")
    public ResponseEntity<ApiResponse<PackageDTO>> getPackageDetail(@PathVariable Integer packageId) {
        PackageDTO response = packageQueryService.getPackageById(packageId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
