package com.hanhwa_tae.secondserver.auth.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;

public interface AuthCommandService {

    void logout(CustomUserDetail userDetail, RefreshTokenRequest refreshToken);
}
