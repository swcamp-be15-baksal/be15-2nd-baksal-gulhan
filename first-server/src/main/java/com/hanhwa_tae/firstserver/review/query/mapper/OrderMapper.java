package com.hanhwa_tae.firstserver.review.query.mapper;

import com.hanhwa_tae.firstserver.common.domain.TargetType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    boolean existsConfirmedOrder(@Param("userNo") Long userNo,
                                 @Param("targetId") int targetId,
                                 @Param("targetType") TargetType targetType);
}
