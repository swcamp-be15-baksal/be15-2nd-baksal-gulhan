package com.hanhwa_tae.secondserver.dashboard.query.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.dashboard.query.dto.request.*;
import com.hanhwa_tae.secondserver.dashboard.query.dto.response.*;
import com.hanhwa_tae.secondserver.dashboard.query.service.DashboardQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "대시보드")
@RestController
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public class DashboardQueryController {

    private final DashboardQueryService dashboardQueryService;

    /* 패키지 판매수량 조회 */
    @Operation(summary = "패키지 판매수량 조회", description = "패키지 별 판매 수량을 조회한다.")
    @GetMapping("/package")
    public ResponseEntity<ApiResponse<PackageQuantityListResponse>> getPackageList(DashboardPackageSearchRequest request) {
        PackageQuantityListResponse response = dashboardQueryService.getPackageList(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 기념품 판매수량 조회 */
    @Operation(summary = "기념품 판매수량 조회", description = "기념품 별 판매 수량을 조회한다.")
    @GetMapping("/goods")
    public ResponseEntity<ApiResponse<GoodsQuantityListResponse>> getGoodsList(DashboardGoodsSearchRequest request) {
        GoodsQuantityListResponse response = dashboardQueryService.getGoodsList(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 월별 수익 */
    @Operation(summary = "월별 수익 조회", description = "월별 판매매출을 조회한다.")
    @GetMapping("/monthly")
    public ResponseEntity<ApiResponse<MonthlyRevenueListResponse>> getMonthly(DashboardMonthlySearchRequest request) {
        MonthlyRevenueListResponse response = dashboardQueryService.getMonthly(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 일별 수익 */
    @Operation(summary = "일별 수익 조회", description = "일별 판매매출을 조회한다.")
    @GetMapping("/daily")
    public ResponseEntity<ApiResponse<DailyRevenueListResponse>> getDaily(DashboardDailySearchRequest request) {
        DailyRevenueListResponse response = dashboardQueryService.getDaily(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 주문내역 - 운송장번호입력을 위한 폼 운송장 번호 = null 조회 */
    @Operation(summary = "주문내역 조회", description = "운송장 번호가 입력되지 않은 주문내역을 조회한다.")
    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<OrderAllListResponse>> getOrderList(DashboardOrderSearchRequest request) {
        OrderAllListResponse response = dashboardQueryService.getOrderList(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
