package com.hanhwa_tae.secondserver.dashboard.query.mapper;

import com.hanhwa_tae.secondserver.dashboard.query.dto.request.*;
import com.hanhwa_tae.secondserver.dashboard.query.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DashboardQueryMapper {

    /* 패키지 판매 수량 및 수익 */
    List<PackageQuantityDTO> selectPackageList(DashboardPackageSearchRequest request);
    long countPackage(DashboardPackageSearchRequest request);

    /* 기념품 판매 수량 및 수익 */
    List<GoodsQuantityDTO> selectGoodsList(DashboardGoodsSearchRequest request);
    long countGoods(DashboardGoodsSearchRequest request);

    /* 월별 기념품 & 패키지 수익 */
    List<MonthlyRevenueDTO> selectMonthlyList(DashboardMonthlySearchRequest request);
    long countMonthly(DashboardMonthlySearchRequest request);

    /* 일별 기념품 & 패키지 수익 */
    List<DailyRevenueDTO> selectDailyList(DashboardDailySearchRequest request);
    long countDaily(DashboardDailySearchRequest request);

    /* 전체 주문내역 조회 - 패키지만 산 사람은 제외 */
    List<OrderAllListDTO> selectOrderList(DashboardOrderSearchRequest request);
    long countOrders(DashboardOrderSearchRequest request);
}
