package com.hanhwa_tae.secondserver.user.command.application.dto.request;

import com.hanhwa_tae.secondserver.user.annotation.ValidEmail;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserFindIdRequest {
    @ValidEmail
    private final String email;
}
