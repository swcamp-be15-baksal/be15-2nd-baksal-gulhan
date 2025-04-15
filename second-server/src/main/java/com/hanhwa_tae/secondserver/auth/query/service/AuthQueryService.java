package com.hanhwa_tae.secondserver.auth.query.service;

import com.hanhwa_tae.secondserver.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.secondserver.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.secondserver.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.secondserver.auth.query.dto.response.AccessTokenResponse;

public interface AuthQueryService {

    TokenResponse login(LoginRequest request);

    AccessTokenResponse reissue(RefreshTokenRequest request);
}
