package com.hanhwa_tae.gulhan.user.command.application.service;

import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserFindIdRequest;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface UserCommandService {

    void registerUser(@Valid UserCreateRequest request) throws MessagingException;

    void verifyByEmail(String uuid);

    void findUserId(UserFindIdRequest request) throws MessagingException;

    void verifyFindUserId(String uuid);
}
