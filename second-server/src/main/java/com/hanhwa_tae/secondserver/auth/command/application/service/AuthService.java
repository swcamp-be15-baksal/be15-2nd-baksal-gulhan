package com.hanhwa_tae.secondserver.auth.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository redisRefreshTokenRepository;


}
