package com.hanhwa_tae.firstserver.goods.command.domain.repository;

import com.hanhwa_tae.firstserver.goods.command.domain.aggregate.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaGoodsRepository extends JpaRepository<Goods, Integer> {
    @Modifying
    @Query("UPDATE Goods g SET g.quantity = g.quantity - :quantity " +
            "WHERE g.goodsId = :targetId ")
    boolean decreaseGoodsQuantity(int quantity, int targetId);

    @Modifying
    @Query("UPDATE Goods g SET g.quantity = g.quantity + :quantity " +
            "WHERE g.goodsId = :targetId ")
    String increaseGoodsQuantity(int quantity, int targetId);
}
