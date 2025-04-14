package com.hanhwa_tae.firstserver.goods.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
public class GoodsDTO {
    private int goodsId;
    private String title;
    private String detail;
    private int quantity;
    private int sold;
    private int remaining;
    private int price;
    private BigDecimal avgRating;
    private Timestamp createdAt;
    private int goodsCategoryId;
    private String goodsCategoryName;
}
