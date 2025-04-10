package com.hanhwa_tae.gulhan.user.command.application.service;

import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import jakarta.validation.Valid;

public interface UserCommandService {

    void registerUser(@Valid UserCreateRequest request);

    void verifyByEmail(String uuid);
}
