package com.hanhwa_tae.gulhan.user.query.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestUserResponse {

    private final String userId;

    private final String password;
}
