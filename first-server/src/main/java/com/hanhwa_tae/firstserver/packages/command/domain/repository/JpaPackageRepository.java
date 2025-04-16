package com.hanhwa_tae.firstserver.packages.command.domain.repository;

import com.hanhwa_tae.firstserver.packages.command.domain.aggregate.Packages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JpaPackageRepository extends JpaRepository<Packages, Integer> {
    @Modifying
    @Query("UPDATE Packages p SET p.capacity = p.capacity + :quantity, " +
            "p.currentRegist = p.currentRegist - :quantity " +
            "WHERE p.packageId = :targetId ")
    String increasePackagesQuantity(int quantity, int targetId);

    @Modifying
    @Query("UPDATE Packages p SET p.capacity = p.capacity - :quantity, " +
            "p.currentRegist = p.currentRegist + :quantity " +
            "WHERE p.packageId = :targetId ")
    void decreasePackagesQuantity(int quantity, int targetId);
}
