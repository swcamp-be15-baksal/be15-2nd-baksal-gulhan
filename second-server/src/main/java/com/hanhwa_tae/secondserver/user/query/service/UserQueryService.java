package com.hanhwa_tae.secondserver.user.query.service;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.user.query.dto.response.*;

public interface UserQueryService {
    UserInfoResponse getUserInfo(CustomUserDetail userDetail);
    RankInfoResponse getRankInfo();

}
