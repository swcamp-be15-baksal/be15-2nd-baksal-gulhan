package com.hanhwa_tae.firstserver.place.query.mapper;


import com.hanhwa_tae.firstserver.place.query.dto.request.AreaSearchRequest;
import com.hanhwa_tae.firstserver.place.query.dto.response.AreaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaMapper {
    List<AreaDto> selectAreas(AreaSearchRequest areaSearchRequest);
}
