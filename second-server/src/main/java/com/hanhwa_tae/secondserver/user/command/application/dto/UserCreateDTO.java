package com.hanhwa_tae.secondserver.user.command.application.dto;

import com.hanhwa_tae.secondserver.user.command.domain.aggregate.GenderType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.LoginType;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.UserInfo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserCreateDTO {
    private final String userId;
    private final String password;
    private final String username;
    private final String email;
    private final GenderType gender;
    private final LoginType loginType;
    private final UserInfo userInfo;
    private Long rankId;
}
