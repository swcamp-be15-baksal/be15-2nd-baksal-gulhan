package com.hanhwa_tae.gulhan.place.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.place.query.dto.request.PlaceSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceDetailResponse;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceListResponse;
import com.hanhwa_tae.gulhan.place.query.service.PlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/place")
public class PlaceQueryController {
    private final PlaceQueryService placeQueryService;

    @GetMapping("/placeDetail/{placeId}")
    public ResponseEntity<ApiResponse<PlaceDetailResponse>> getPlace(@PathVariable Long placeId) {
        PlaceDetailResponse response = placeQueryService.getPlace(placeId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/places")
    public ResponseEntity<ApiResponse<PlaceListResponse>> getPlaces(
            PlaceSearchRequest placeSearchRequest
    ) {
        PlaceListResponse response = placeQueryService.getPlaces(placeSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
