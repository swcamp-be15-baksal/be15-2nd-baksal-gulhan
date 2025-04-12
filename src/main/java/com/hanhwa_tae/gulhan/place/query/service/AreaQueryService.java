package com.hanhwa_tae.gulhan.place.query.service;

import com.hanhwa_tae.gulhan.place.query.dto.request.AreaSearchRequest;
import com.hanhwa_tae.gulhan.place.query.dto.response.AreaDto;
import com.hanhwa_tae.gulhan.place.query.dto.response.AreaListResponse;
import com.hanhwa_tae.gulhan.place.query.mapper.AreaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaQueryService {
    private final AreaMapper areaMapper;

    @Transactional(readOnly = true)
    public AreaListResponse getAreas(AreaSearchRequest areaSearchRequest) {

        List<AreaDto> areas = areaMapper.selectAreas(areaSearchRequest);

        return AreaListResponse.builder()
                .areas(areas)
                .build();
    }
}
