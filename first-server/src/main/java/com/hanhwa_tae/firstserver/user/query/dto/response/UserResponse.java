package com.hanhwa_tae.firstserver.user.query.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserResponse {
    private final String username;
    private final String email;


}
