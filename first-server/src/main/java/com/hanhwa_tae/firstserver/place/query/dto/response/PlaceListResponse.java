package com.hanhwa_tae.firstserver.place.query.dto.response;

import com.hanhwa_tae.firstserver.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PlaceListResponse {
    private final List<PlacesDto> places;
    private final Pagination pagination;
}
