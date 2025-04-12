package com.hanhwa_tae.gulhan.auth.command.application.dto.request;

import com.hanhwa_tae.gulhan.user.annotation.ValidBirth;
import com.hanhwa_tae.gulhan.user.annotation.ValidEmail;
import com.hanhwa_tae.gulhan.user.annotation.ValidPhone;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.GenderType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KakaoAdditionalInfoRequest {

    @NotBlank(message = "userId는 필수 값입니다.")
    private String userId;

    @ValidEmail
    private String email;

    private GenderType gender;

    @ValidBirth
    private String birth;

    @ValidPhone
    private String phone;

    private String address;
}
