package com.hanhwa_tae.firstserver.packages.query.controller;

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
class PackageQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("패키지 목록 조회 - 필터 및 정렬 적용")
    void getPackageList_withFiltersAndSort() throws Exception {
        mockMvc.perform(get("/packages/list")
                        .param("title", "경복궁")
                        .param("area", "서울")
                        .param("sort", "like")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.packages").isArray())
                .andExpect(jsonPath("$.data.pagination.currentPage").value(1))
                .andExpect(jsonPath("$.data.pagination.size").value(10));
    }

    @Test
    @DisplayName("패키지 상세 조회 - 유효 ID")
    void getPackageDetail_validId() throws Exception {
        int packageId = 1;

        mockMvc.perform(get("/packages/list/{packageId}", packageId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.title").isString())
                .andExpect(jsonPath("$.data.area").isString())
                .andExpect(jsonPath("$.data.remaining").isNumber())
                .andExpect(jsonPath("$.data.avgRating").isNumber());
    }

    @Test
    @DisplayName("패키지 상세 조회 실패 - 존재하지 않는 ID")
    void getPackageDetail_notFound() throws Exception {
        int invalidPackageId = 999999;

        mockMvc.perform(get("/packages/list/{packageId}", invalidPackageId))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorCode").exists())
                .andExpect(jsonPath("$.message").exists());
    }
}
