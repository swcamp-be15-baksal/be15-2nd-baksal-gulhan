package com.hanhwa_tae.secondserver.dashboard.command.application.service;

import com.hanhwa_tae.secondserver.dashboard.command.application.dto.request.OrderUpdateRequest;
import com.hanhwa_tae.secondserver.dashboard.query.mapper.DashboardOrderMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardCommandService {

    private final DashboardOrderMapper dashboardOrderMapper;

    /* 운송장 번호, 주문코드, 받는사람 정보 수정 */
    @Transactional
    public void updateOrder(String orderId, OrderUpdateRequest orderUpdateRequest) {

        dashboardOrderMapper.updateOrder(orderId, orderUpdateRequest);
    }
}
