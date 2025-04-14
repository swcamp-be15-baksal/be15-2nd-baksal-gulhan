package com.hanhwa_tae.secondserver.user.command.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockUser;
import com.hanhwa_tae.secondserver.user.command.application.dto.request.UpdateUserInfoRequest;
import com.hanhwa_tae.secondserver.user.command.application.dto.request.UserCreateRequest;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.GenderType;
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

@ActiveProfiles("local")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserCommandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 테스트")
    void testRegister() throws Exception {
        // given
        UserCreateRequest request = UserCreateRequest.builder()
                .userId("user99")
                .username("조현승")
                .password("pass99")
                .email("toki1999032@gmail.com")
                .gender(GenderType.M)
                .phone("010-1231-1231")
                .birth("1999-01-01")
                .address("경기도 안양")
                .countryCode("82")
                .build();
        //when
        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
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

    @Test
    @DisplayName("유저 정보 업데이트")
    @WithCustomMockUser
    void testUpdateUserInfo() throws Exception {
        UpdateUserInfoRequest request = UpdateUserInfoRequest.builder()
                .password("pass0001")
                .phone("010-1234-5677")
                .address("경기도 수원시")
                .build();

        mockMvc.perform(put("/api/v1/users/info/update")
//                        .with(SecurityMockMvcRequestPostProcessors())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }
}