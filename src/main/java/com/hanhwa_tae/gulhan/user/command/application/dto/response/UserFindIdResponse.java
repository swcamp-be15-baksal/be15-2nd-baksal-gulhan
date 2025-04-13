package com.hanhwa_tae.gulhan.user.command.application.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserFindIdResponse {

    private final String maskedUserId;
}
