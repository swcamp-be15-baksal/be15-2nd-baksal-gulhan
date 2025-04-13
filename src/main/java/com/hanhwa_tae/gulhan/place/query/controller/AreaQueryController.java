package com.hanhwa_tae.gulhan.place.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.place.query.dto.request.AreaSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.AreaListResponse;
import com.hanhwa_tae.gulhan.place.query.service.AreaQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "장소")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/areas")
public class AreaQueryController {
    private final AreaQueryService areaQueryService;

    @Operation(summary = "지역 목록 조회", description = "지역 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<AreaListResponse>> getAreas(
            AreaSearchRequest areaSearchRequest
    ) {
        AreaListResponse response = areaQueryService.getAreas(areaSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
