package com.hanhwa_tae.gulhan.packages.query.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageSearchRequest {
    private String location;         // 지역
    private String date;             // 날짜 (혹은 시작일 기준 필터링용)
    private String sortType;         // 정렬 조건 (like, review, price 등)

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
