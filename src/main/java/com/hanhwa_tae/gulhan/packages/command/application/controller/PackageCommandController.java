package com.hanhwa_tae.gulhan.packages.command.application.controller;


import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.packages.command.application.service.PackageCommandService;
import com.hanhwa_tae.gulhan.packages.query.dto.request.PackageInsertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/packages")
@RequiredArgsConstructor
public class PackageCommandController {

    private final PackageCommandService packageCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> insertPackage(@RequestBody PackageInsertRequest request) {
        return ResponseEntity.ok(ApiResponse.success(packageCommandService.insertPackage(request)));
    }

    @PutMapping("/list/{packageId}")
    public ResponseEntity<ApiResponse<Void>> updatePackage (@PathVariable Integer packageId, @RequestBody PackageInsertRequest request) {
        packageCommandService.updatePackage(packageId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/list/{packageId}")
    public ResponseEntity<ApiResponse<Void>> deletePackage(@PathVariable Integer packageId){
        packageCommandService.deletePackage(packageId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
