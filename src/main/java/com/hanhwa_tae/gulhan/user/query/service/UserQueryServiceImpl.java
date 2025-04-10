package com.hanhwa_tae.gulhan.user.query.service;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.dto.request.LoginRequestUserRequest;
import com.hanhwa_tae.gulhan.user.query.dto.response.LoginRequestUserResponse;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserMapper userMapper;

    @Override
    public LoginRequestUserResponse findLoginRequestUser(LoginRequestUserRequest user) {
        User foundUser = userMapper.findUserByUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호를 확인해주세요."));

        LoginRequestUserResponse response = LoginRequestUserResponse.builder()
                .userId(foundUser.getUserId())
                .password(foundUser.getPassword())
                .build();

        return response;
    }
}
