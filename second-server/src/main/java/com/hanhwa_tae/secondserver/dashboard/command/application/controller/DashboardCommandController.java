package com.hanhwa_tae.secondserver.dashboard.command.application.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.dashboard.command.application.dto.request.OrderUpdateRequest;
import com.hanhwa_tae.secondserver.dashboard.command.application.service.DashboardCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "대시보드")
@RestController
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class DashboardCommandController {

    private final DashboardCommandService dashboardCommandService;

    @Operation(summary = "주문내역 수정사항 입력", description = "받는 사람 정보 수정 및 운송장 번호를 입력한다. ")
    @PutMapping("/{orderId}")
    public ResponseEntity<ApiResponse<Void>> updateOrder(
            @PathVariable Long orderId,
            @RequestBody @Validated OrderUpdateRequest orderUpdateRequest
            ) {

        dashboardCommandService.updateOrder(orderId, orderUpdateRequest);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
