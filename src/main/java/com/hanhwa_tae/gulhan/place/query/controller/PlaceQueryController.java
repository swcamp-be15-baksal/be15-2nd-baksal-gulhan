package com.hanhwa_tae.gulhan.place.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.place.query.dto.request.PlaceSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceDetailResponse;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceListResponse;
import com.hanhwa_tae.gulhan.place.query.service.PlaceQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "장소")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/place")
public class PlaceQueryController {
    private final PlaceQueryService placeQueryService;

    @Operation(summary = "장소 상세 조회", description = "해당 장소를 상세 조회 한다.")
    @GetMapping("/placeDetail/{placeId}")
    public ResponseEntity<ApiResponse<PlaceDetailResponse>> getPlace(@PathVariable Long placeId) {
        PlaceDetailResponse response = placeQueryService.getPlace(placeId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "장소 목록 조회", description = "장소 목록을 조회한다.")
    @GetMapping("/places")
    public ResponseEntity<ApiResponse<PlaceListResponse>> getPlaces(
            PlaceSearchRequest placeSearchRequest
    ) {
        PlaceListResponse response = placeQueryService.getPlaces(placeSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
