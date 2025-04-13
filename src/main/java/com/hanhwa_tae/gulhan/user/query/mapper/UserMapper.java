package com.hanhwa_tae.gulhan.user.query.mapper;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.review.command.domain.aggregate.Review;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.Rank;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.dto.response.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    Optional<User> findUserByUserId(String userId);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByPhone(String phone);

    Rank findRankIdByRankName(String rankName);

    Optional<UserInfoResponse> findUserInfoByUserId(String userId);

    Optional<User> findUserByUserNo(Long userNo);

    List<Rank> findAllRank();

    List<Review> findReviewByUserNoAndTargetType(Long userNo, TargetType targetType);
}
