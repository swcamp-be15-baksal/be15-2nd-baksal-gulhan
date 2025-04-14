package com.hanhwa_tae.secondserver.notice.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockAdmin;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockUser;
import com.hanhwa_tae.secondserver.notice.command.application.dto.request.NoticeInsertRequest;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("local")
@Transactional
@AutoConfigureMockMvc
class NoticeCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("공지사항 등록 (관리자만)")
    @WithCustomMockUser
    void createNotice() throws Exception {
        // given
        NoticeInsertRequest request = NoticeInsertRequest.builder()
                .title("제목입력")
                .content("내용입력")
                .build();

        //when
        mockMvc.perform(post("/notice/list")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("공지사항 수정 (관리자만)")
    @WithCustomMockAdmin
    void updateNotice() throws Exception {

        int noticeId = 1;
        // given
        NoticeInsertRequest request = NoticeInsertRequest.builder()
                .title("제목입력")
                .content("내용입력")
                .build();

        //when
        mockMvc.perform(put("/notice/list/{noticeId}", noticeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공지사항 삭제 (관리자만)")
    @WithCustomMockAdmin
    void deleteNotice() throws Exception {

        long noticeId = 1;

        mockMvc.perform(delete("/notice/list/{noticeId}", noticeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}