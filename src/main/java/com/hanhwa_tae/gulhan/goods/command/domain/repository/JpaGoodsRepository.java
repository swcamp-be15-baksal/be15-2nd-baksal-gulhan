package com.hanhwa_tae.gulhan.goods.command.domain.repository;

import com.hanhwa_tae.gulhan.goods.command.domain.aggregate.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGoodsRepository extends JpaRepository<Goods, Integer> {
}
