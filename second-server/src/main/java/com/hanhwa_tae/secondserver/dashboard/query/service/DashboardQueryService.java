package com.hanhwa_tae.secondserver.dashboard.query.service;

import com.hanhwa_tae.secondserver.common.dto.Pagination;
import com.hanhwa_tae.secondserver.dashboard.query.dto.request.*;
import com.hanhwa_tae.secondserver.dashboard.query.dto.response.*;
import com.hanhwa_tae.secondserver.dashboard.query.mapper.DashboardQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardQueryService {

    private final DashboardQueryMapper dashboardQueryMapper;

    public PackageQuantityListResponse getPackageList(DashboardPackageSearchRequest request) {
        List<PackageQuantityDTO> paackgeQuantityDTOList= dashboardQueryMapper.selectPackageList(request);
        long totalPosts = dashboardQueryMapper.countPackage(request);

        int page = request.getPage();
        int size = request.getSize();
        return PackageQuantityListResponse.builder()
                .packageList(paackgeQuantityDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .build())
                .build();
    }

    public GoodsQuantityListResponse getGoodsList(DashboardGoodsSearchRequest request) {
        List<GoodsQuantityDTO> goodsQuantityDTOList= dashboardQueryMapper.selectGoodsList(request);
        long totalPosts = dashboardQueryMapper.countGoods(request);

        int page = request.getPage();
        int size = request.getSize();
        return GoodsQuantityListResponse.builder()
                .goodsQuantityList(goodsQuantityDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .build())
                .build();
    }

    public MonthlyRevenueListResponse getMonthly(DashboardMonthlySearchRequest request) {
        List<MonthlyRevenueDTO> quantityDTOList= dashboardQueryMapper.selectMonthlyList(request);
        long totalPosts = dashboardQueryMapper.countMonthly(request);

        int page = request.getPage();
        int size = request.getSize();
        return MonthlyRevenueListResponse.builder()
                .monthlyRevenueList(quantityDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .build())
                .build();
    }

    public DailyRevenueListResponse getDaily(DashboardDailySearchRequest request) {
        List<DailyRevenueDTO> quantityDTOList= dashboardQueryMapper.selectDailyList(request);
        long totalPosts = dashboardQueryMapper.countDaily(request);

        int page = request.getPage();
        int size = request.getSize();
        return DailyRevenueListResponse.builder()
                .dailyRevenueList(quantityDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .build())
                .build();
    }

    public OrderAllListResponse getOrderList(DashboardOrderSearchRequest request) {
        List<OrderAllListDTO> orderList= dashboardQueryMapper.selectOrderList(request);
        long totalPosts = dashboardQueryMapper.countOrders(request);

        int page = request.getPage();
        int size = request.getSize();
        return OrderAllListResponse.builder()
                .orderAllList(orderList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .build())
                .build();
    }
}
