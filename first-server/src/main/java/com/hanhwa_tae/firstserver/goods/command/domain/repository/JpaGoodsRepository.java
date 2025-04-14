package com.hanhwa_tae.firstserver.goods.command.domain.repository;

import com.hanhwa_tae.firstserver.goods.command.domain.aggregate.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGoodsRepository extends JpaRepository<Goods, Integer> {
}
