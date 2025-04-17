package com.hanhwa_tae.secondserver.user.command.application.dto.request;


import com.hanhwa_tae.secondserver.user.annotation.ValidBirth;
import com.hanhwa_tae.secondserver.user.annotation.ValidEmail;
import com.hanhwa_tae.secondserver.user.annotation.ValidPhone;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.GenderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @ValidEmail
    private String email;

    @NotNull
    private GenderType gender;

    @NotBlank
    @ValidBirth
    private String birth;

    @NotBlank
    @ValidPhone
    /* 휴대폰 값 검증 따로 생성 */
    private String phone;

    private String address;

    @NotBlank
    /* default 값 지정*/
    private String countryCode;

    public void setEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
