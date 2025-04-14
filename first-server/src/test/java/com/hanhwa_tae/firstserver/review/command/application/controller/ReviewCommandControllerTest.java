package com.hanhwa_tae.firstserver.review.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import com.hanhwa_tae.firstserver.review.query.dto.request.ReviewInsertRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Transactional
class ReviewCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ReviewInsertRequest createRequest() {
        ReviewInsertRequest request = new ReviewInsertRequest();
        request.setTargetId(1);
        request.setTargetType(TargetType.GOODS);
        request.setDetail("좋은 상품이었습니다.");
        request.setRating(BigDecimal.valueOf(4.5));
        request.setUserNo(100L);
        return request;
    }

    @Test
    @DisplayName("리뷰 등록 성공")
    void insertReview() throws Exception {
        ReviewInsertRequest request = createRequest();

        mockMvc.perform(post("/review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .requestAttr("userNo", 100L)) // CustomUserDetail ?
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isNumber());
    }

    @Test
    @DisplayName("리뷰 수정 성공")
    void updateReview() throws Exception {
        int reviewId = 1;
        ReviewInsertRequest request = createRequest();
        request.setDetail("수정된 리뷰 내용");
        request.setRating(BigDecimal.valueOf(3.8));

        mockMvc.perform(put("/review/{reviewId}", reviewId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .requestAttr("userNo", 100L)) // CustomUserDetail ?
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("리뷰 삭제 성공")
    void deleteReview() throws Exception {
        int reviewId = 1;
        Long userNo = 100L;

        mockMvc.perform(delete("/review/{reviewId}", reviewId)
                        .param("userNo", String.valueOf(userNo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
