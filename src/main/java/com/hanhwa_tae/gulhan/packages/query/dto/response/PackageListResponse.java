package com.hanhwa_tae.gulhan.packages.query.dto.response;
import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class PackageListResponse {
    private final List<PackageDTO> packages;
    private final Pagination pagination;
}
