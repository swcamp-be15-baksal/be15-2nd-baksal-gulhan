package com.hanhwa_tae.gulhan.goods.command.domain.aggregate;

import com.hanhwa_tae.gulhan.common.domain.DeleteType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodsId;

    private String title;

    @Lob
    private String detail;

    private int quantity;

    private int price;

    @CreatedDate
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N; // enum

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_category_id")
    private GoodsCategory goodsCategoryId; // FK
}