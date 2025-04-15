package com.hanhwa_tae.secondserver.travelmatepost.query.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("local")
class TmpQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("동행글 목록 조회")
    @WithMockUser
    void travelMatePostList() throws Exception {

        mockMvc.perform(get("/board/list")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.tmpList").isArray())
                .andExpect(jsonPath("$.data.tmpList.length()").value(greaterThan(0)))
                .andExpect(jsonPath("$.data.pagination").exists());
    }

    @Test
    @DisplayName("동행글 상세 조회")
    @WithMockUser
    void getTmpList() throws Exception {
        mockMvc.perform(get("/board/list/{travelMatePostId}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.tmpDetailDTO").isMap())
                .andExpect(jsonPath("$.data.tmpDetailDTO.length()").value(greaterThan(0)));
    }
}