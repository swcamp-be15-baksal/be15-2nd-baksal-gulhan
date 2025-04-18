package com.hanhwa_tae.secondserver.auth.query.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
    private final String userId;
    private final String password;
}
