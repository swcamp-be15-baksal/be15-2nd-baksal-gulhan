package com.hanhwa_tae.gulhan.user.command.application.service;

import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService{
    @Override
    public void registerUser(UserCreateRequest request) {

    }

    @Override
    public void verifyByEmail(String uuid) {

    }
}
