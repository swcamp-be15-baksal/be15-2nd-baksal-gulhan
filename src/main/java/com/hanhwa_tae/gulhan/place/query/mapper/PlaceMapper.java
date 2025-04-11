package com.hanhwa_tae.gulhan.place.query.mapper;

import com.hanhwa_tae.gulhan.place.query.dto.request.PlaceSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.PlaceDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaceMapper {
    List<PlaceDto> selectPlaces(PlaceSearchRequest placeSearchRequest);

    long countPlaces(PlaceSearchRequest placeSearchRequest);
}
