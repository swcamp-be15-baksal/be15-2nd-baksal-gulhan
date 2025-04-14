package com.hanhwa_tae.firstserver.goods.query.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.util.List;

@Getter
@Setter
public class GoodsSearchRequest {
    private String title;
    private String detail;
    private Integer quantity;
    private Integer sold;
    private Integer remaining;
    private Integer price;
    private Integer goodsCategoryId;
    private Timestamp createdAt;

    private Integer page = 1;
    private Integer size = 20;

    private Integer limit;
    private Integer offset;

    public void calculatePaging() {
        this.limit = size;
        this.offset = (page-1) * size;
    }

    private String sort; // 정렬, 좋아요순/리뷰순
    private List<Integer> categoryFilter; // 특정 카테고리만 보여주도록
}
