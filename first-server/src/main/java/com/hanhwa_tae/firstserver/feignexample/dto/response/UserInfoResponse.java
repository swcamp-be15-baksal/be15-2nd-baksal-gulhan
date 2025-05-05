package com.hanhwa_tae.firstserver.feignexample.dto.response;

import com.hanhwa_tae.firstserver.common.dto.GenderType;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class UserInfoResponse {
    private final String userId;

    private final String username;
    private final String email;
    private final GenderType gender;
    private final Date birth;
    private final String phone;
    private final String address;

    @Override
    public String toString() {
        return "UserInfoResponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}