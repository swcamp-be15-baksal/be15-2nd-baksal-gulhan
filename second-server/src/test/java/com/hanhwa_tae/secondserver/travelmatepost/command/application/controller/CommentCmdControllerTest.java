package com.hanhwa_tae.secondserver.travelmatepost.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockUser;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.CommentInsertRequest;
import com.hanhwa_tae.secondserver.travelmatepost.command.application.dto.request.CommentUpdateRequest;
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
class CommentCmdControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("댓글 작성")
    @WithCustomMockUser
    void insertComment() throws Exception {
        //given
        CommentInsertRequest request = CommentInsertRequest.builder()
                .parentCommentId(null)
                .content("댓글")
                .build();

        mockMvc.perform(post("/comment/{travelMatePostId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("본인 댓글 수정")
    @WithCustomMockUser
    void updateComment() throws Exception {
        // given
        CommentUpdateRequest request = CommentUpdateRequest.builder()
                .content("댓글수정내용")
                .build();

        mockMvc.perform(put("/comment/{commentId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("본인 댓글 삭제")
    @WithCustomMockUser
    void deleteComment() throws Exception {
        mockMvc.perform(delete("/comment/{commentId}",1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}