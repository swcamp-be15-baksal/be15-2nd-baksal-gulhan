package com.hanhwa_tae.firstserver.payment.command.infrastructure.repository;


import com.hanhwa_tae.firstserver.packages.command.domain.aggregate.Packages;
import com.hanhwa_tae.firstserver.payment.command.domain.repository.PackagesPaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPackagesPaymentRepository extends PackagesPaymentRepository, JpaRepository<Packages,Integer> {
}