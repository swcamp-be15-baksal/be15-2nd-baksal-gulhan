package com.hanhwa_tae.gulhan.user.command.application.dto.request;


import com.hanhwa_tae.gulhan.user.annotation.ValidBirth;
import com.hanhwa_tae.gulhan.user.annotation.ValidEmail;
import com.hanhwa_tae.gulhan.user.annotation.ValidPhone;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.GenderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {
    @NotBlank
    private final String userId;

    @NotBlank
    private final String userName;

    @NotBlank
    private final String password;

    @NotBlank
    @ValidEmail
    private final String email;

    @NotNull
    private final GenderType gender;

    @NotBlank
    @ValidBirth
    private final String birth;

    @NotBlank
    @ValidPhone
    /* 휴대폰 값 검증 따로 생성 */
    private final String phone;

    private final String address;

    @NotBlank
    /* default 값 지정*/
    private final String countryCode;
}
