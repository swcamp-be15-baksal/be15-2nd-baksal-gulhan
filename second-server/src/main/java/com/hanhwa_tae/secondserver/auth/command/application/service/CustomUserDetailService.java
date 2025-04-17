package com.hanhwa_tae.secondserver.auth.command.application.service;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public CustomUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("유저 찾기 시작 : " + username);

        User user = userMapper.findUserByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저 찾지 못함"));
        log.info("발견한 유저 : " +  user.getUserId());
        return CustomUserDetail.builder()
                .userId(user.getUserId())
                .userNo(user.getUserNo())
                .password(user.getPassword())
                .authorities(Collections.singleton(
                        new SimpleGrantedAuthority(
                                user.getRank()
                                        .getRankName()
                                        .name())))
                .build();
    }
}
