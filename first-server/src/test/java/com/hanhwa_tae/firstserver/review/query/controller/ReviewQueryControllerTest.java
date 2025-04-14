package com.hanhwa_tae.firstserver.review.query.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
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
class ReviewQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("리뷰 목록 조회 - targetId & targetType 기준")
    void getReviewList_success() throws Exception {
        mockMvc.perform(get("/review")
                        .param("targetId", "1")
                        .param("targetType", TargetType.GOODS.name())  // 또는 PACKAGE
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.review").isArray())
                .andExpect(jsonPath("$.data.pagination.currentPage").value(1))
                .andExpect(jsonPath("$.data.pagination.size").value(10));
    }

    @Test
    @DisplayName("리뷰 목록 조회 실패 - 잘못된 targetType")
    void getReviewList_invalidTargetType() throws Exception {
        mockMvc.perform(get("/review")
                        .param("targetId", "1")
                        .param("targetType", "INVALID_TYPE"))
                .andExpect(status().is4xxClientError());
    }
}
