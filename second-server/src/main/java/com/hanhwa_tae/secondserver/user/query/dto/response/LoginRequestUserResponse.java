package com.hanhwa_tae.secondserver.user.query.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestUserResponse {

    private final String userId;

    private final String password;
}
