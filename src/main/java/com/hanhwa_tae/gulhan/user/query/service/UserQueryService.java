package com.hanhwa_tae.gulhan.user.query.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.user.query.dto.request.LoginRequestUserRequest;
import com.hanhwa_tae.gulhan.user.query.dto.response.LoginRequestUserResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.RankInfoResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserInfoResponse;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserReviewResponse;

public interface UserQueryService {
    UserInfoResponse getUserInfo(CustomUserDetail userDetail);

    UserReviewResponse getUserReview(CustomUserDetail userDetail, TargetType targetType);

    RankInfoResponse getRankInfo();
}
