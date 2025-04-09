package com.hanhwa_tae.gulhan.utils.jwt;

import com.hanhwa_tae.gulhan.auth.command.application.service.CustomUserDetailService;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token =getJwtFromRequest(request);

        // 토큰 검증
        // **유효한 경우** if 문 수행
        if(StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)){
            String userId = jwtTokenProvider.getUserIdFromJWT(token);
            CustomUserDetail userDetail = userDetailsService.loadUserByUsername(userId);
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(
                    userDetail, null,userDetail.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
