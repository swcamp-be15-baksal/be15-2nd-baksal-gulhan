package com.hanhwa_tae.gulhan.user.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.ChangeUserPasswordRequest;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UpdateUserInfoRequest;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserFindIdRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface UserCommandService {

    void registerUser(@Valid UserCreateRequest request) throws MessagingException;

    void verifyByEmail(String uuid);

    void updateUserInfo(CustomUserDetail userDetail, UpdateUserInfoRequest request);

    void changeUserPassword(CustomUserDetail userDetail, @Valid ChangeUserPasswordRequest request);

    void withdrawUser(CustomUserDetail userDetail);

    void findUserId(UserFindIdRequest request) throws MessagingException;

    String verifyFindUserId(String uuid);
}
