package com.hanhwa_tae.firstserver.user.query.mapper;


import com.hanhwa_tae.firstserver.user.command.domain.aggregate.Rank;
import com.hanhwa_tae.firstserver.user.query.dto.response.UserDetailResponse;
import com.hanhwa_tae.firstserver.user.query.dto.response.UserResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<Long> findUserNoByUserId(String id);
    Optional<UserResponse> findUserInfoByUserId(String userId);

    Optional<UserDetailResponse> findUserInfoDetailByUserId(String userId);
    Optional<Rank> findALLRankInfo(Long userNo);
}
