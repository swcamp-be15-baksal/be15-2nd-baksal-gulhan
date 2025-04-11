package com.hanhwa_tae.gulhan.user.query.mapper;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.Rank;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.UserInfo;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> findUserByUserId(String userId);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByPhone(String phone);

    Rank findRankIdByRankName(String rankName);

    Optional<UserInfoResponse> findUserInfoByUserId(String userId);
}
