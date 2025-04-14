package com.hanhwa_tae.secondserver.user.command.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanhwa_tae.secondserver.annotation.WithCustomMockUser;
import com.hanhwa_tae.secondserver.user.command.application.dto.request.*;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.GenderType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
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
    @Transactional
        // 롤백 시키기
    void testUpdateUserInfo() throws Exception {
        UpdateUserInfoRequest request = UpdateUserInfoRequest.builder()
                .password("pass01")
                .phone("010-1234-5677")
                .address("경기도 수원시")
                .build();

        mockMvc.perform(put("/users/info/update")
//                        .with(SecurityMockMvcRequestPostProcessors())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비밀번호 찾기")
    void testFindUserPassword() throws Exception {
        UserFindPasswordRequest request = UserFindPasswordRequest.builder()
                .userId("user02")
                .email("toki0327123@naver.com")
                .build();

        mockMvc.perform(post("/users/find/password").contentType(MediaType.APPLICATION_JSON)
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
    @DisplayName("아이디 찾기")
    void testFindUserId() throws Exception {
        UserFindIdRequest request = UserFindIdRequest.builder()
                .email("toki0327123@naver.com")
                .build();

        //when
        mockMvc.perform(post("/users/find/id")
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
    @WithCustomMockUser
    @Transactional
    @DisplayName("비밀번호 변경")
    void testChangeUserPassword() throws Exception {
        ChangeUserPasswordRequest request1 = ChangeUserPasswordRequest.builder()
                .password("pass01")
                .confirmPassword("pass01")
                .build();

        /* 비밀번호 확인과 다름 오류 */
//        ChangeUserPasswordRequest request2 = ChangeUserPasswordRequest.builder()
//                .password("pass01")
//                .confirmPassword("pass02")
//                .build();

        mockMvc.perform(put("/users/change/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request1)))
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
    @DisplayName("회원 탈퇴")
    @WithCustomMockUser
    @Transactional
    void testUserWithdraw() throws Exception {
        mockMvc.perform(get("/users/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.errorCode").doesNotExist())
                .andExpect(jsonPath("$.data").doesNotExist())
                .andExpect(jsonPath("$.message").doesNotExist())
                .andExpect(jsonPath("$.timestamp").exists());
    }
}