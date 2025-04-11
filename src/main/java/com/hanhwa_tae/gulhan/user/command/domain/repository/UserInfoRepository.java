package com.hanhwa_tae.gulhan.user.command.domain.repository;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.UserInfo;

public interface UserInfoRepository {
    UserInfo save(UserInfo userInfo);

    UserInfo findByUserNo(Long userNo);
}
