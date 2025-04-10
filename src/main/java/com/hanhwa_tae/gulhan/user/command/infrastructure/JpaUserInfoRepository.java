package com.hanhwa_tae.gulhan.user.command.infrastructure;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.UserInfo;
import com.hanhwa_tae.gulhan.user.command.domain.repository.UserInfoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserInfoRepository extends UserInfoRepository, JpaRepository<UserInfo, User> {
}
