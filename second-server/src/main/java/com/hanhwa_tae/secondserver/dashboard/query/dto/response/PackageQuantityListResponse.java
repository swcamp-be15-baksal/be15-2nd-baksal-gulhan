package com.hanhwa_tae.secondserver.dashboard.query.dto.response;

import com.hanhwa_tae.secondserver.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PackageQuantityListResponse {

    private final List<PackageQuantityDTO> packageList;

    private final Pagination pagination;
}
