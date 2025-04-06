package com.hanhwa_tae.gulhan.travelmatepost.command.domain.repository;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
    User findByUserNo(int userNo);
}
