package com.hanhwa_tae.secondserver.delivery.command.domain.repository;

import com.hanhwa_tae.secondserver.delivery.command.domain.aggregate.DeliveryAddress;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    Optional<DeliveryAddress> findByUser(User user);

    @Modifying
    @Transactional
    @Query("UPDATE DeliveryAddress da SET da.defaultAddress = 'N' WHERE da.user = :user AND da.defaultAddress = 'Y'")
    void updateAllDefaultAddressToN(User user);

    Optional<DeliveryAddress> findByDeliveryAddressIdAndUser(Integer deliveryAddressId, User user);
}
