package com.hanhwa_tae.gulhan.user.me.query.mapper;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import com.hanhwa_tae.gulhan.review.command.domain.aggregate.Review;
import com.hanhwa_tae.gulhan.travelmatepost.command.domain.aggregate.Comment;
import com.hanhwa_tae.gulhan.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.dto.PackageOrderHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    List<PackageOrderHistoryDTO> findPackageOrderHistoryByUserNo(Long userNo);

    List<GoodsOrderHistoryDTO> findGoodsOrderHistoryByUserNo(Long userNo);

    int updateIsConfirmed(int orderHistoryId);


    List<Review> findReviewByUserNoAndTargetType(Long userNo, TargetType targetType);

    List<Comment> findCommentByUserNo(Long userNo);
}
