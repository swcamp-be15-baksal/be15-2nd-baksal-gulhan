package com.hanhwa_tae.secondserver.user.command.application.dto.response;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserFindIdResponse {

    private final String maskedUserId;
}
