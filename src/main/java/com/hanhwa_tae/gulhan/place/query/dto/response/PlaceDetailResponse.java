package com.hanhwa_tae.gulhan.place.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaceDetailResponse {
    private final PlaceDto place;
}
