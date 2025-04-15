package com.hanhwa_tae.firstserver.payment.command.domain.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;

public interface GoodsPaymentRepository {

    @Query("select price from Goods where goodsId = :id")
    Integer findPriceById(@Param("id") Integer id);

}
