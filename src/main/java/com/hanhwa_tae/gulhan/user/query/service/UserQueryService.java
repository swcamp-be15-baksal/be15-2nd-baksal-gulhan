package com.hanhwa_tae.gulhan.user.query.service;

import com.hanhwa_tae.gulhan.user.query.dto.request.LoginRequestUserRequest;
import com.hanhwa_tae.gulhan.user.query.dto.response.LoginRequestUserResponse;

public interface UserQueryService {

    LoginRequestUserResponse findLoginRequestUser(LoginRequestUserRequest request);
}
