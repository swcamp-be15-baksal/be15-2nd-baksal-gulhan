package com.hanhwa_tae.gulhan.payment.command.infrastructure.repository;


import com.hanhwa_tae.gulhan.goods.command.domain.aggregate.Goods;
import com.hanhwa_tae.gulhan.payment.command.domain.repository.GoodsPaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGoodsPaymentRepository extends GoodsPaymentRepository, JpaRepository<Goods,Integer> {
}