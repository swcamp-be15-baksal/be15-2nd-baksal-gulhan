package com.hanhwa_tae.gulhan.dashboard.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardDailySearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardGoodsSearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardMonthlySearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardOrderSearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardPackageSearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.DailyRevenueListResponse;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.GoodsQuantityListResponse;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.MonthlyRevenueListResponse;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.OrderAllListResponse;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.PackageQuantityListResponse;
import com.hanhwa_tae.gulhan.dashboard.query.service.DashboardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/dashboard")
@RequiredArgsConstructor
public class DashboardQueryController {

    private final DashboardQueryService dashboardQueryService;

    /* 패키지 판매수량 조회 */
    @GetMapping("/package")
    public ResponseEntity<ApiResponse<PackageQuantityListResponse>> getPackageList(DashboardPackageSearchRequest request) {
        PackageQuantityListResponse response = dashboardQueryService.getPackageList(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 기념품 판매수량 조회 */
    @GetMapping("/goods")
    public ResponseEntity<ApiResponse<GoodsQuantityListResponse>> getGoodsList(DashboardGoodsSearchRequest request) {
        GoodsQuantityListResponse response = dashboardQueryService.getGoodsList(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 월별 수익 */
    @GetMapping("/monthly")
    public ResponseEntity<ApiResponse<MonthlyRevenueListResponse>> getMonthly(DashboardMonthlySearchRequest request) {
        MonthlyRevenueListResponse response = dashboardQueryService.getMonthly(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 일별 수익 */
    @GetMapping("/daily")
    public ResponseEntity<ApiResponse<DailyRevenueListResponse>> getDaily(DashboardDailySearchRequest request) {
        DailyRevenueListResponse response = dashboardQueryService.getDaily(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /* 주문내역 - 운송장번호입력을 위한 폼 운송장 번호 = null 조회 */
    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<OrderAllListResponse>> getOrderList(DashboardOrderSearchRequest request) {
        OrderAllListResponse response = dashboardQueryService.getOrderList(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
