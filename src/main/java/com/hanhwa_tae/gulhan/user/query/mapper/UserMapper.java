package com.hanhwa_tae.gulhan.user.query.mapper;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.Rank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Rank findRankIdByRankName(String rankName);
}
