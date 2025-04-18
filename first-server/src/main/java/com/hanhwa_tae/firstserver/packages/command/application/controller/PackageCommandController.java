package com.hanhwa_tae.firstserver.packages.command.application.controller;



import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.packages.command.application.service.PackageCommandService;
import com.hanhwa_tae.firstserver.packages.query.dto.request.PackageInsertRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "패키지")
@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor
public class PackageCommandController {

    private final PackageCommandService packageCommandService;

    @Operation(summary = "패키지 등록", description = "패키지를 새로 등록한다. (관리자만)")
    @PostMapping
    public ResponseEntity<ApiResponse<Integer>> insertPackage(@RequestBody PackageInsertRequest request) {
        return ResponseEntity.ok(ApiResponse.success(packageCommandService.insertPackage(request)));
    }

    @Operation(summary = "패키지 수정", description = "수정사항이 있는 패키지를 수정한다. (관리자만)")
    @PutMapping("/list/{packageId}")
    public ResponseEntity<ApiResponse<Void>> updatePackage (@PathVariable Integer packageId, @RequestBody PackageInsertRequest request) {
        packageCommandService.updatePackage(packageId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "패키지 삭제", description = "패키지를 삭제한다. (관리자만)")
    @DeleteMapping("/list/{packageId}")
    public ResponseEntity<ApiResponse<Void>> deletePackage(@PathVariable Integer packageId){
        packageCommandService.deletePackage(packageId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
