package com.hanhwa_tae.gulhan.user.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.ChangeUserPasswordRequest;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UpdateUserInfoRequest;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import jakarta.validation.Valid;

public interface UserCommandService {

    void registerUser(@Valid UserCreateRequest request);

    void verifyByEmail(String uuid);

    void updateUserInfo(CustomUserDetail userDetail, UpdateUserInfoRequest request);

    void chageUserPassword(CustomUserDetail userDetail, @Valid ChangeUserPasswordRequest request);

    void withdrawUser(CustomUserDetail userDetail);
}
