package com.hanhwa_tae.gulhan.auth.query.service;

import com.hanhwa_tae.gulhan.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.gulhan.auth.query.dto.request.LoginRequest;

public interface AuthQueryService {

    TokenResponse login(LoginRequest request);
}
