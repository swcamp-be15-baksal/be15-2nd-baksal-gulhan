package com.hanhwa_tae.firstserver.place.query.controller;

import com.hanhwa_tae.firstserver.place.query.dto.request.AreaSearchRequest;
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
class PlaceQueryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("장소 상세 조회")
    void testGetParentAreas() throws Exception {
        AreaSearchRequest areaSearchRequest = AreaSearchRequest.builder()
                .build();

        mockMvc.perform(get("/place/placeDetail/{placeId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.place").isMap())
                .andExpect(jsonPath("$.data.place.length()").value(greaterThan(0)));
    }

    @Test
    @DisplayName("장소 목록 조회")
    void testGetAreas() throws Exception {

        mockMvc.perform(get("/place/places?page=1&size=10&category=MUSEUM&areaId=50")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.places").isArray())
                .andExpect(jsonPath("$.data.places.length()").value(greaterThan(0)));
    }

}