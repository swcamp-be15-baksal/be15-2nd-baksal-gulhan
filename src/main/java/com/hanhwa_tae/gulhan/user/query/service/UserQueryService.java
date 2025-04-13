package com.hanhwa_tae.gulhan.user.query.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.user.query.dto.response.RankInfoResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserInfoResponse;

public interface UserQueryService {
    UserInfoResponse getUserInfo(CustomUserDetail userDetail);

    RankInfoResponse getRankInfo();
}
