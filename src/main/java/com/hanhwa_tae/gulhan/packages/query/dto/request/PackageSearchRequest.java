package com.hanhwa_tae.gulhan.packages.query.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PackageSearchRequest {
    private String area;
    private String title;
    private Timestamp startDate;
    private Timestamp endDate;

    private Integer page = 1;
    private Integer size = 20;

    private Integer limit;
    private Integer offset;

    public void calculatePaging() {
        this.limit = size;
        this.offset = (page-1) * size;
    }

    private String sort;
}
