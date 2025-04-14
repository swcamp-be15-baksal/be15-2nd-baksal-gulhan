package com.hanhwa_tae.firstserver.goods.query.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Transactional
class GoodsQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("기념품 목록 조회 - 정렬 및 카테고리 필터 적용")
    void getGoodsList_withFiltersAndSort() throws Exception {
        mockMvc.perform(get("/goods/list")
                        .param("title", "머그")
                        .param("goodsCategoryId", "1")
                        .param("sort", "like")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.goodsList").isArray())
                .andExpect(jsonPath("$.data.pagination.currentPage").value(1))
                .andExpect(jsonPath("$.data.pagination.size").value(10));
    }

    @Test
    @DisplayName("기념품 상세 조회 - 유효 ID")
    void getGoodsDetail_validId() throws Exception {
        int goodsId = 1;

        mockMvc.perform(get("/goods/list/{goodsId}", goodsId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").isString())
                .andExpect(jsonPath("$.data.detail").isString())
                .andExpect(jsonPath("$.data.price").isNumber())
                .andExpect(jsonPath("$.data.remaining").isNumber())
                .andExpect(jsonPath("$.data.avgRating").isNumber());
    }

    @Test
    @DisplayName("기념품 상세 조회 실패 - 존재하지 않는 ID")
    void getGoodsDetail_notFound() throws Exception {
        int invalidGoodsId = 999999;

        mockMvc.perform(get("/goods/list/{goodsId}", invalidGoodsId))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").exists())
                .andExpect(jsonPath("$.message").exists());
    }
}
