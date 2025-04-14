package com.hanhwa_tae.secondserver.user.query.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserQueryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithCustomMockUser
    @DisplayName("회원 정보 조회")
    void testUserInfo() throws Exception {
        mockMvc.perform(get("/users/info")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").doesNotExist())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.userId").exists())
                .andExpect(jsonPath("$.data.username").exists())
                .andExpect(jsonPath("$.data.email").exists())
                .andExpect(jsonPath("$.data.gender").exists())
                .andExpect(jsonPath("$.data.birth").exists())
                .andExpect(jsonPath("$.data.phone").exists())
                .andExpect(jsonPath("$.data.address").exists())
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    @DisplayName("등급 목록 전체 조회")
    void getRankInfo() throws Exception {
        mockMvc.perform(get("/users/ranks")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").doesNotExist())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.rankList").exists())
                .andExpect(jsonPath("$.data.rankList").isNotEmpty())
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.timestamp").exists());
    }
}