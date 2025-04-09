package com.hanhwa_tae.gulhan.auth.query.service;

import com.hanhwa_tae.gulhan.auth.command.application.dto.request.RefreshTokenRequest;
import com.hanhwa_tae.gulhan.auth.command.application.dto.response.TokenResponse;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.auth.query.dto.request.LoginRequest;
import com.hanhwa_tae.gulhan.auth.query.dto.response.AccessTokenResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthQueryService {

    TokenResponse login(LoginRequest request);

    AccessTokenResponse reissue(CustomUserDetail userDetail, RefreshTokenRequest request);
}
