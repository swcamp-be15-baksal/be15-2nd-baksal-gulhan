package com.hanhwa_tae.gulhan.payment.command.infrastructure;


import com.hanhwa_tae.gulhan.packages.command.domain.aggregate.Packages;
import com.hanhwa_tae.gulhan.payment.command.domain.repository.PackagesPaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPackagesPaymentRepository extends PackagesPaymentRepository, JpaRepository<Packages,Integer> {
}