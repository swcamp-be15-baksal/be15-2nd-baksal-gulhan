package com.hanhwa_tae.gulhan.review.query.mapper;

import com.hanhwa_tae.gulhan.common.domain.TargetType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    boolean existsConfirmedOrder(@Param("id") String id,
                                 @Param("targetId") int targetId,
                                 @Param("targetType") TargetType targetType);
}
