package com.hanhwa_tae.firstserver.packages.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.firstserver.packages.query.dto.request.PackageInsertRequest;
import com.hanhwa_tae.firstserver.common.dto.GenderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Transactional
class PackageCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private PackageInsertRequest createRequest() {
        PackageInsertRequest request = new PackageInsertRequest();
        request.setTitle("창경궁 야행 투어");
        request.setPrice(30000);
        request.setDetail("창경궁의 밤");
        request.setCapacity(10);
        request.setArea("서울");
        request.setStartDate(Timestamp.valueOf("2025-06-01 18:00:00"));
        request.setEndDate(Timestamp.valueOf("2025-06-01 20:00:00"));
        request.setGuideName("삼다수");
        request.setGuideEmail("guide@example.com");
        request.setGuideGender(GenderType.F);
        request.setGuidePhone("010-1234-5678");
        return request;
    }

    @Test
    @DisplayName("패키지 등록 성공")
    void insertPackage() throws Exception {
        PackageInsertRequest request = createRequest();

        mockMvc.perform(post("/packages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isNumber());
    }

    @Test
    @DisplayName("패키지 수정 성공")
    void updatePackage() throws Exception {
        int packageId = 1;
        PackageInsertRequest request = createRequest();
        request.setTitle("수정된 야행 투어 패키지");

        mockMvc.perform(put("/packages/list/{packageId}", packageId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("패키지 삭제 성공")
    void deletePackage() throws Exception {
        int packageId = 1;

        mockMvc.perform(delete("/packages/list/{packageId}", packageId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
