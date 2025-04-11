package com.hanhwa_tae.gulhan.place.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.place.query.dto.request.AreaSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.AreaListResponse;
import com.hanhwa_tae.gulhan.place.query.service.AreaQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/areas")
public class AreaQueryController {
    private final AreaQueryService areaQueryService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<AreaListResponse>> getAreas(
            AreaSearchRequest areaSearchRequest
    ) {
        AreaListResponse response = areaQueryService.getAreas(areaSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
