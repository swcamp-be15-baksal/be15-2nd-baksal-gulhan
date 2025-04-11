package com.hanhwa_tae.gulhan.goods.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "goods_category")
@Getter
@Setter
public class GoodsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodsCategoryId;

    private String categoryName;
}