package com.hanhwa_tae.secondserver.dashboard.command.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockAdmin;
import com.hanhwa_tae.secondserver.dashboard.command.application.dto.request.OrderUpdateRequest;
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

@SpringBootTest
@ActiveProfiles("local")
@Transactional
@AutoConfigureMockMvc
class DashboardCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("운송장 번호 입력")

    void updateOrder() throws Exception {
        //given
        OrderUpdateRequest orderUpdateRequest = OrderUpdateRequest.builder()
                .shippingNo("12345")
                .address("집주소")
                .receiverPhone("01036479057")
                .receiver("이승재")
                .build();

        //when
        mockMvc.perform(post("/admin/order/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderUpdateRequest)))
                .andDo(MockMvcResultHandlers.print())
                //then
                .andExpect(status().isOk())
                /* https://0soo.tistory.com/190 */
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").doesNotExist())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.timestamp").exists());
    }
}