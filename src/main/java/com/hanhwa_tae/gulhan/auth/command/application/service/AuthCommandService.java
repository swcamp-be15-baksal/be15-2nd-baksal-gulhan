package com.hanhwa_tae.gulhan.auth.command.application.service;

import com.hanhwa_tae.gulhan.auth.command.application.dto.request.RefreshTokenRequest;

public interface AuthCommandService {

    void logout(RefreshTokenRequest refreshToken);
}
