package com.hanhwa_tae.secondserver.security;


import com.hanhwa_tae.secondserver.security.model.CustomUserDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String userNo = request.getHeader("X-User-No");
        String userId = request.getHeader("X-User-Id");
        String rank = request.getHeader("X-User-Rank");


        if(userNo != null && userId != null && rank != null){
            CustomUserDetail userDetail = CustomUserDetail.builder()
                    .userNo(Long.valueOf(userNo))
                    .userId(userId)
                    .authorities(Collections.singleton(new SimpleGrantedAuthority(rank)))
                    .build();
            PreAuthenticatedAuthenticationToken authentication
                    = new PreAuthenticatedAuthenticationToken(
                    userDetail, null,userDetail.getAuthorities()
            );
            logger.info("필터 수행 유저 NO : " + userDetail.getUserNo());
            logger.info("필터 수행 유저 ID : " + userDetail.getUserId());
            logger.info("필터 수행 유저 RANK : " + userDetail.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
