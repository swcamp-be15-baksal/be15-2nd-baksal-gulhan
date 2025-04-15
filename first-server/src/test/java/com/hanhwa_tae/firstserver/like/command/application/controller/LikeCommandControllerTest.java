
package com.hanhwa_tae.firstserver.like.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.firstserver.annotation.WithCustomMockUser;
import com.hanhwa_tae.firstserver.common.domain.TargetType;
import com.hanhwa_tae.firstserver.like.command.application.dto.request.LikeCreateRequest;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LikeCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithCustomMockUser
    void toggleLike() throws Exception {
        //given
        LikeCreateRequest likeCreateRequest = LikeCreateRequest.builder()
                .targetId(1)
                .targetType(TargetType.PACKAGE)
                .build();

        //when
        mockMvc.perform(post("/like/toggle")
//                        .with(SecurityMockMvcRequestPostProcessors())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(likeCreateRequest)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}