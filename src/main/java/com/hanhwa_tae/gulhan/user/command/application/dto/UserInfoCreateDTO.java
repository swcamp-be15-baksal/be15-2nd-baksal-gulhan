package com.hanhwa_tae.gulhan.user.command.application.dto;

import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserInfoCreateDTO {
    private final User user;
    private final String birth;
    private final String phone;
    private final String address;
    private final String countryCode;
}
