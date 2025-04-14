package com.hanhwa_tae.secondserver.travelmatepost.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockUser;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.TmpInsertRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.TmpUpdateRequest;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("local")
class TpmCmdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("동행글 작성")
    @WithCustomMockUser
    void createPost() throws Exception {
        //given
        TmpInsertRequest request = TmpInsertRequest.builder()
                .title("title")
                .content("content")
                .build();

        //when
        mockMvc.perform(post("/board")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("본인 동행글 수정")
    @WithCustomMockUser
    void updatePost() throws Exception {

        // given
        TmpUpdateRequest request = TmpUpdateRequest.builder()
                .title("title")
                .content("content")
                .build();

        //when
        mockMvc.perform(put("/board/list/{travelMatePostId}",4) // 본인이 작성한 게시글이어야함
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("본인 동행글 삭제")
    @WithCustomMockUser
    void deletePost() throws Exception {

        mockMvc.perform(delete("/board/list/{travelMatePostId}",4) // 본인이 작성한 게시글이어야함
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}