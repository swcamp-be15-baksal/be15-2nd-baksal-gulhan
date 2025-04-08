package com.hanhwa_tae.gulhan.travelmatepost.command.domain.repository;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserInfoRepository extends JpaRepository<UserInfo, Integer> {
}
