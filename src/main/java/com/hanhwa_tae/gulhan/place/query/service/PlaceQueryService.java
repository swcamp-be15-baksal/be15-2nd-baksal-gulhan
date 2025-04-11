package com.hanhwa_tae.gulhan.place.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.place.query.dto.request.PlaceSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceDto;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceListResponse;
import com.hanhwa_tae.gulhan.place.query.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceQueryService {
    private final PlaceMapper placeMapper;

    /* 장소 목록 조회*/
    @Transactional(readOnly = true)
    public PlaceListResponse getPlaces(PlaceSearchRequest placeSearchRequest) {

        // 필요한 컨텐츠 조회
        List<PlaceDto> places = placeMapper.selectPlaces(placeSearchRequest);
        // 검색 결과가 총 몇개가 있는지
        long totalPlaces = placeMapper.countPlaces(placeSearchRequest);

        int page = placeSearchRequest.getPage();
        int size = placeSearchRequest.getSize();

        return PlaceListResponse.builder()
                .places(places)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int)Math.ceil((double) totalPlaces / size))
                        .size(size)
                        .build())
                .build();
    }
}
