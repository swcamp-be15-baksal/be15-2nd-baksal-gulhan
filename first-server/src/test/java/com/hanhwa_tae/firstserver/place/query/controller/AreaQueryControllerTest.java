package com.hanhwa_tae.firstserver.place.query.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.firstserver.place.query.dto.request.AreaSearchRequest;
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

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AreaQueryControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("부모 지역 목록 조회")
    void testGetParentAreas() throws Exception {
        AreaSearchRequest areaSearchRequest = AreaSearchRequest.builder()
                .build();

        mockMvc.perform(get("/areas/list")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.areas").isArray())
                .andExpect(jsonPath("$.data.areas[0].areaId").value(1L))
                .andExpect(jsonPath("$.data.areas[0].areaName").value("서울"));
    }

    @Test
    @DisplayName("지역 목록 조회")
    void testGetAreas() throws Exception {

        mockMvc.perform(get("/areas/list?parentAreaId=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.areas").isArray())
                .andExpect(jsonPath("$.data.areas.length()").value(greaterThan(0)));
    }

}