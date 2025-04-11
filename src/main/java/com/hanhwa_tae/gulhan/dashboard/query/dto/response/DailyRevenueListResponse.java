package com.hanhwa_tae.gulhan.dashboard.query.dto.response;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.common.dto.Pagination;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class DailyRevenueListResponse {

    private final List<DailyRevenueDTO> dailyRevenueList;

    private final Pagination pagination;
}
