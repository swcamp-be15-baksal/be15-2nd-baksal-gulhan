package com.hanhwa_tae.secondserver.user.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.user.command.application.dto.request.*;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface UserCommandService {

    void registerUser(@Valid UserCreateRequest request) throws MessagingException;

    void verifyByEmail(String uuid);

    void updateUserInfo(CustomUserDetail userDetail, UpdateUserInfoRequest request);

    void findUserId(UserFindIdRequest request) throws MessagingException;

    String verifyFindUserId(String uuid);

    void changeUserPassword(CustomUserDetail userDetail, @Valid ChangeUserPasswordRequest request);

    void findUserPassword(@Valid UserFindPasswordRequest request) throws MessagingException;

    void withdrawUser(CustomUserDetail userDetail);

}
