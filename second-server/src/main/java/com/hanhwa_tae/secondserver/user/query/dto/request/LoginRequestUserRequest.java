package com.hanhwa_tae.secondserver.user.query.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class LoginRequestUserRequest {
    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
