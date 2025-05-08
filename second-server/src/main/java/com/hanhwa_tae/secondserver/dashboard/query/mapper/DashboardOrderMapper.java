package com.hanhwa_tae.secondserver.dashboard.query.mapper;

import com.hanhwa_tae.secondserver.dashboard.command.application.dto.request.OrderUpdateRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DashboardOrderMapper {
    int updateOrder(@Param("orderId") String orderId,
                    @Param("request") OrderUpdateRequest request);
}
