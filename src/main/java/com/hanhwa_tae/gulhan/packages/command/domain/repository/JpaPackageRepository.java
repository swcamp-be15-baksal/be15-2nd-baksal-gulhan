package com.hanhwa_tae.gulhan.packages.command.domain.repository;

import com.hanhwa_tae.gulhan.packages.command.domain.aggregate.Packages;
import org.springframework.data.jpa.repository.JpaRepository;

public class JpaPackageRepository extends JpaRepository<Packages, String> {

}
