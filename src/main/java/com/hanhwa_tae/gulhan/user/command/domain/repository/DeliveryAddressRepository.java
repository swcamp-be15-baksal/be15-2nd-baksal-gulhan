package com.hanhwa_tae.gulhan.user.command.domain.repository;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.DeliveryAddress;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    Optional<DeliveryAddress> findByUser(User user);
}
