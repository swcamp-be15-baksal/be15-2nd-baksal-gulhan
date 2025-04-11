package com.hanhwa_tae.gulhan.goods.query.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class GoodsDTO {
    private int goodsId;
    private String title;
    private String detail;
    private int quantity;
    private int price;
    private Timestamp createdAt;
    private int goodsCategoryId;
    private String goodsCategoryName;
}
