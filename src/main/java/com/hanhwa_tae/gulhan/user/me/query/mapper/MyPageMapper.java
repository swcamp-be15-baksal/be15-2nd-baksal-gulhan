package com.hanhwa_tae.gulhan.user.me.query.mapper;

import com.hanhwa_tae.gulhan.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.gulhan.user.me.query.dto.PackageOrderHistoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    List<PackageOrderHistoryDTO> findPackageOrderHistoryByUserNo(Long userNo);

    List<GoodsOrderHistoryDTO> findGoodsOrderHistoryByUserNo(Long userNo);

    List<Review> findReviewByUserNoAndTargetType(Long userNo, TargetType targetType);

    List<Comment> findCommentByUserNo(Long userNo);
}
