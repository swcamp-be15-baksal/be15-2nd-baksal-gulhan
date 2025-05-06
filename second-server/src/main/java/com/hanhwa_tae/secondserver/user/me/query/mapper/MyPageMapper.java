package com.hanhwa_tae.secondserver.user.me.query.mapper;

import com.hanhwa_tae.secondserver.common.domain.TargetType;
import com.hanhwa_tae.secondserver.delivery.query.dto.response.DeliveryAddressDTO;
import com.hanhwa_tae.secondserver.user.me.query.dto.GoodsOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.me.query.dto.PackageOrderHistoryDTO;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserBoardDTO;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserCommentDTO;
import com.hanhwa_tae.secondserver.user.query.dto.response.UserReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageMapper {

    List<PackageOrderHistoryDTO> findPackageOrderHistoryByUserNo(Long userNo);

    List<GoodsOrderHistoryDTO> findGoodsOrderHistoryByUserNo(Long userNo);

    List<UserReviewDTO> findReviewByUserNoAndTargetType(Long userNo, TargetType targetType);

    List<UserCommentDTO> findCommentByUserNo(Long userNo);

    List<DeliveryAddressDTO> findDeliveryAddressByUserNo(Long userNo);

    List<UserBoardDTO> findBoardByUserNo(Long userNo);
}
