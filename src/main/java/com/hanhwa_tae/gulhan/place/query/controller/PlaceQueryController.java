package com.hanhwa_tae.gulhan.place.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.place.query.dto.request.PlaceSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceListResponse;
import com.hanhwa_tae.gulhan.place.query.service.PlaceQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlaceQueryController {
    private final PlaceQueryService placeQueryService;

    @GetMapping("/places")
    public ResponseEntity<ApiResponse<PlaceListResponse>> getPlaces(
            PlaceSearchRequest placeSearchRequest
    ) {
        PlaceListResponse response = placeQueryService.getPlaces(placeSearchRequest);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
