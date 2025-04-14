package com.hanhwa_tae.secondserver.dashboard.query.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockAdmin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
@AutoConfigureMockMvc
class DashboardQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("패키지 판매수량 조회")
    @WithCustomMockAdmin
    void getPackageList() throws Exception {
        mockMvc.perform(get("/admin/dashboard/package")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.packageList").isArray())
                .andExpect(jsonPath("$.data.packageList.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.data.pagination").exists());
    }

    @Test
    @DisplayName("기념품 판매수량 조회")
    @WithCustomMockAdmin
    void getGoodsList() throws Exception {
        mockMvc.perform(get("/admin/dashboard/goods")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.goodsQuantityList").isArray())
                .andExpect(jsonPath("$.data.goodsQuantityList.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.data.pagination").exists());
    }

    @Test
    @DisplayName("월별 수익 조회")
    @WithCustomMockAdmin
    void getMonthly() throws Exception {
        mockMvc.perform(get("/admin/dashboard/monthly")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.monthlyRevenueList").isArray())
                .andExpect(jsonPath("$.data.monthlyRevenueList.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.data.pagination").exists());
    }


    @Test
    @DisplayName("일별 수익 조회")
    @WithCustomMockAdmin
    void getDaily() throws Exception {
        mockMvc.perform(get("/admin/dashboard/daily")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.dailyRevenueList").isArray())
                .andExpect(jsonPath("$.data.dailyRevenueList.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.data.pagination").exists());
    }

    @Test
    @DisplayName("주문내역 조회")
    @WithCustomMockAdmin
    void getOrderList() throws Exception {
        mockMvc.perform(get("/admin/dashboard/orders")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.orderAllList").isArray())
                .andExpect(jsonPath("$.data.orderAllList.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.data.pagination").exists());
    }
}