package com.hanhwa_tae.firstserver.place.query.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AreaSearchRequest {
    private Long parentAreaId;
}
