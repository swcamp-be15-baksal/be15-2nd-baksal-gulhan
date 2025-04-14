package com.hanhwa_tae.secondserver.user.command.application.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeUserPasswordRequest {

    @NotBlank
    private String password;

    @NotBlank
    private String confirmPassword;
}
