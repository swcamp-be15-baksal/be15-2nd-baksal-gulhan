package com.hanhwa_tae.gulhan.packages.query.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PackageSearchRequest {
    private String area;
    private Timestamp startDate;
    private Timestamp endDate;

    private Integer page = 1;
    private Integer size = 20;
    private String title;

    public int getOffset() {
        return (page - 1) * size;
    }

    public int getLimit() {
        return size;
    }
}
