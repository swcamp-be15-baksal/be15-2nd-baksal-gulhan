package com.hanhwa_tae.firstserver.payment.command.domain.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.Query;

public interface PackagesPaymentRepository {

    @Query("select price from Packages where packageId = :id")
    Integer findPriceById(@Param("id") Integer id);

}
