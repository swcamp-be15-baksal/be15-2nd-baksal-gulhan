package com.hanhwa_tae.gulhan.goods.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsUpdateRequest {
    private String title;
    private String detail;
    private int quantity;
    private int price;
    private int goodsCategoryId;
}
