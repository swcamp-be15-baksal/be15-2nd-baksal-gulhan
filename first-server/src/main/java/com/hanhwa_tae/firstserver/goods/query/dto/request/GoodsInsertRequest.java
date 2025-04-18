package com.hanhwa_tae.firstserver.goods.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsInsertRequest {
    private String title;
    private String detail;
    private int quantity;
    private int sold;
    private int remaining;
    private int price;
    private int goodsCategoryId;
}
