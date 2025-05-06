package com.hanhwa_tae.firstserver.goods.command.domain.aggregate;


import com.hanhwa_tae.firstserver.common.domain.DeleteType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Table(name = "goods")
@Getter
@Setter
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goodsId;

    private String title;

    @Lob
    private String detail;

    private int quantity;

    private int sold;

    private int remaining;

    private int price;


    @CreatedDate
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    private DeleteType isDeleted = DeleteType.N; // enum

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_category_id")
    private GoodsCategory goodsCategoryId; // FK

    public void updateGoods(@NotBlank String title, @NotBlank String detail, @NotBlank int quantity,
                            @NotBlank int remaining, @NotBlank int price, @NotBlank GoodsCategory goodsCategoryId) {
        this.title = title;
        this.detail = detail;
        this.quantity = quantity;
        this.remaining = remaining;
        this.price = price;
        this.goodsCategoryId = goodsCategoryId;
    }
}
