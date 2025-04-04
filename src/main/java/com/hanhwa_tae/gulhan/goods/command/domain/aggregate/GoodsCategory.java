package com.hanhwa_tae.gulhan.goods.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
@Table(name = "goods_category")
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodsCategoryId;

    private String categoryName;
}