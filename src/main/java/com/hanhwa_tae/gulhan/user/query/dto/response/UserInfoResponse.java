package com.hanhwa_tae.gulhan.user.query.dto.response;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.GenderType;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class UserInfoResponse {
    private final String userId;

    private final String userName;
    private final String email;
    private final GenderType gender;
    private final Date birth;
    private final String phone;
    private final String address;
}
