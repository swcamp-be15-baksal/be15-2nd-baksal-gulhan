package com.hanhwa_tae.firstserver.like.query.controller;

import com.hanhwa_tae.firstserver.annotation.WithCustomMockUser;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LikeQueryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("관심 목록 조회")
    @WithCustomMockUser
    void testLikeList() throws Exception {

        mockMvc.perform(get("/like/likes?page=1&size=10&targetType=GOODS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.likes").isArray())
                .andExpect(jsonPath("$.data.likes.length()").value(greaterThan(0)));
    }


}