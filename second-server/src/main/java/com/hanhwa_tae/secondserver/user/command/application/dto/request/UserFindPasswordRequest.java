package com.hanhwa_tae.secondserver.user.command.application.dto.request;


import com.hanhwa_tae.secondserver.user.annotation.ValidEmail;
import com.hanhwa_tae.secondserver.user.annotation.ValidUserId;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class UserFindPasswordRequest {

    @ValidUserId
    private final String userId;

    @ValidEmail
    private final String email;
}
