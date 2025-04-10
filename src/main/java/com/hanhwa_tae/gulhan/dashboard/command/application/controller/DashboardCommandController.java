package com.hanhwa_tae.gulhan.dashboard.command.application.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.dashboard.command.application.dto.request.OrderUpdateRequest;
import com.hanhwa_tae.gulhan.dashboard.command.application.service.DashboardCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/order")
@RequiredArgsConstructor
public class DashboardCommandController {

    private final DashboardCommandService dashboardCommandService;

    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Void>> updateOrder(
            @PathVariable Long orderId,
            @RequestBody @Validated OrderUpdateRequest orderUpdateRequest
            ) {
        dashboardCommandService.updateOrder(orderId, orderUpdateRequest);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
