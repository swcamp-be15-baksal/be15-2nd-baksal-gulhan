package com.hanhwa_tae.gulhan.place.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaDto {
    private Long areaId;
    private String areaCode;
    private String areaName;
    private Long parentAreaId;
}
