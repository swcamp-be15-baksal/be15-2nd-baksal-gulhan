package com.hanhwa_tae.gulhan.user.command.application.dto.request;

import com.hanhwa_tae.gulhan.user.annotation.ValidPhone;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserInfoRequest {
    @NotBlank
    private final String password;

    @ValidPhone
    private final String phone;

    private final String address;
}
