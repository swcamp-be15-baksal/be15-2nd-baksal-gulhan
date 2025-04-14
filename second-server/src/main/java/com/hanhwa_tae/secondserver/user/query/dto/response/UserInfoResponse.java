package com.hanhwa_tae.secondserver.user.query.dto.response;

import com.hanhwa_tae.secondserver.user.command.domain.aggregate.GenderType;
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
}
