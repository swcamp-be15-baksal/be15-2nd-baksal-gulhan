package com.hanhwa_tae.gulhan.dashboard.query.mapper;

import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardDailySearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardGoodsSearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardMonthlySearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardOrderSearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.request.DashboardPackageSearchRequest;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.DailyRevenueDTO;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.GoodsQuantityDTO;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.MonthlyRevenueDTO;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.OrderAllListDTO;
import com.hanhwa_tae.gulhan.dashboard.query.dto.response.PackageQuantityDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardQueryMapper {

    /* 패키지 판매 수량 및 수익 */
    List<PackageQuantityDTO> selectPackageList(DashboardPackageSearchRequest request);
    int countPackage(DashboardPackageSearchRequest request);

    /* 기념품 판매 수량 및 수익 */
    List<GoodsQuantityDTO> selectGoodsList(DashboardGoodsSearchRequest request);
    int countGoods(DashboardGoodsSearchRequest request);

    /* 월별 기념품 & 패키지 수익 */
    List<MonthlyRevenueDTO> selectMonthlyList(DashboardMonthlySearchRequest request);
    int countMonthly(DashboardMonthlySearchRequest request);

    /* 일별 기념품 & 패키지 수익 */
    List<DailyRevenueDTO> selectDailyList(DashboardDailySearchRequest request);
    int countDaily(DashboardDailySearchRequest request);

    /* 전체 주문내역 조회 - 패키지만 산 사람은 제외 */
    List<OrderAllListDTO> selectOrderList(DashboardOrderSearchRequest request);
    int countOrders(DashboardOrderSearchRequest request);
}
