package com.hanhwa_tae.firstserver.user.query.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDetailResponse {
    private final String address;
    private final String phone;
    private final int point;
}
