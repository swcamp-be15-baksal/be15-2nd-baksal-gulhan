package com.hanhwa_tae.gulhan.user.command.application.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
public class UserFindPasswordRequest {
    private final String userId;
    private final String email;
}
