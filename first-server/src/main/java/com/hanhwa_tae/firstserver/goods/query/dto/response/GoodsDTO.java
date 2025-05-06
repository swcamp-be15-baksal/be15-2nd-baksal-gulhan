package com.hanhwa_tae.firstserver.goods.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
public class GoodsDTO {
    private Integer goodsId;
    private String title;
    private String detail;
    private Integer quantity;
    private Integer sold;
    private Integer remaining;
    private Integer price;
    private BigDecimal avgRating;
    private Timestamp createdAt;
    private Integer goodsCategoryId;
    private String goodsCategoryName;

    private Integer likeCount;

    private Integer reviewCount;
}
