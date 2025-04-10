package com.hanhwa_tae.gulhan.auth.query.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private final String userId;
    private final String password;
}
