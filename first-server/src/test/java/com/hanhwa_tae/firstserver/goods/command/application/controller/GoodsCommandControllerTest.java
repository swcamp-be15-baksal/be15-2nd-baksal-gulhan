package com.hanhwa_tae.firstserver.goods.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsInsertRequest;
import com.hanhwa_tae.firstserver.goods.query.dto.request.GoodsUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("local")
@Transactional
class GoodsCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("기념품 등록 성공")
    void insertGoods() throws Exception {
        GoodsInsertRequest request = new GoodsInsertRequest();
        request.setTitle("기념 우산");
        request.setDetail("로고가 인쇄된 고급 자동우산");
        request.setPrice(20000);
        request.setQuantity(100);
        request.setSold(0);
        request.setGoodsCategoryId(1);

        mockMvc.perform(post("/goods")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("기념품 수정 성공")
    void updateGoods() throws Exception {
        int goodsId = 1;

        GoodsUpdateRequest request = new GoodsUpdateRequest();
        request.setTitle("업데이트된 우산");
        request.setDetail("기능이 추가된 자동우산");
        request.setPrice(22000);
        request.setQuantity(80);
        request.setSold(20);
        request.setGoodsCategoryId(1);

        mockMvc.perform(put("/goods/list/{goodsId}", goodsId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    @DisplayName("기념품 삭제 성공")
    void deleteGoods() throws Exception {
        int goodsId = 1;

        mockMvc.perform(delete("/goods/list/{goodsId}", goodsId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}
