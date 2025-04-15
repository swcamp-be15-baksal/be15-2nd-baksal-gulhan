package com.hanhwa_tae.secondserver.notice.query.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
@AutoConfigureMockMvc
class NoticeQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("공지사항 목록 조회")
    @WithCustomMockUser
    void getNoticeList() throws Exception {
        mockMvc.perform(get("/notice/list")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.tmpList").isArray())
                .andExpect(jsonPath("$.data.tmpList.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.data.pagination").exists());
    }

    @Test
    @DisplayName("공지사항 상세 조회")
    @WithCustomMockUser
    void getNoticeDetail() throws Exception {
        int noticeId = 1;

        mockMvc.perform(get("/notice/list/{noticeId}", noticeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.noticeDTO").isMap())
                .andExpect(jsonPath("$.data.noticeDTO.length()").value(greaterThan(0)));
    }
}