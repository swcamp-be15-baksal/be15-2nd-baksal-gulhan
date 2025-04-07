package com.hanhwa_tae.gulhan.auth.query.service;

import com.hanhwa_tae.gulhan.auth.query.dto.request.LoginRequest;

public interface AuthQueryService {

    void login(LoginRequest request);
}
