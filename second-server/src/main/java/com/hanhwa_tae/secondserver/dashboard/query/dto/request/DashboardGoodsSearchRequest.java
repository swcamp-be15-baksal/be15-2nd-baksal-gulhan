package com.hanhwa_tae.secondserver.dashboard.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DashboardGoodsSearchRequest {
    private Integer page = 1;
    private Integer size = 20;

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }
}
