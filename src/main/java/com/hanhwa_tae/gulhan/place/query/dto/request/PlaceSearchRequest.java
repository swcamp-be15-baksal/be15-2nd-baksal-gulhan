package com.hanhwa_tae.gulhan.place.query.dto.request;

import com.hanhwa_tae.gulhan.place.command.domain.aggregate.CategoryType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceSearchRequest {
    private Integer page = 1;
    private Integer size = 10;
    /* 부모 지역 아이디 ex) 강원도 1, 경기도 2, 충청북도 3, 경상북도 4 ... */
    private Long parentAreaId;
    /* 지역 아이디 ex) 철원 11, 수원 22, 청주 33, 상주 44 ... */
    private Long areaId;
    /* 카테고리 별 조회 MUSEUM, HISTORIC_SITE, FOLK_VILLAGE */
    @Enumerated(EnumType.STRING)
    private CategoryType category;
    /* 그 외 조건 추가하면 됨 */
    private String title;

    public int getOffset() { return (page - 1) * size; }

    public int getLimit() { return size; }
}
