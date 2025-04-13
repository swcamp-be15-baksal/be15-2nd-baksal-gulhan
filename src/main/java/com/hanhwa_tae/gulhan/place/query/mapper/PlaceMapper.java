package com.hanhwa_tae.gulhan.place.query.mapper;

import com.hanhwa_tae.gulhan.place.query.dto.request.PlaceSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceDto;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlacesDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {

    /* 장소 상세 조회 */
    PlaceDto selectPlaceById(Long placeId);

    /* 검색/페이징 조건을 전달받아 장소 조회 */
    List<PlacesDto> selectPlaces(PlaceSearchRequest placeSearchRequest);

    long countPlaces(PlaceSearchRequest placeSearchRequest);
}
