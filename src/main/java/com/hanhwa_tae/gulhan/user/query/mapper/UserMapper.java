package com.hanhwa_tae.gulhan.user.query.mapper;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.Rank;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    Rank findRankIdByRankName(String rankName);

    Optional<User> findUserByUserId(String userId);
}
