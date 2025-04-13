package com.hanhwa_tae.gulhan.utils.jwt;

import com.hanhwa_tae.gulhan.auth.command.application.service.CustomUserDetailService;
import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService userDetailsService;
    private final JwtErrorResponse jwtErrorResponse;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token =getJwtFromRequest(request);
        logger.info("토큰 정보 : " + token);
        // 토큰 검증
        // **유효한 경우** if 문 수행


        if(StringUtils.hasText(token) && validateToken(response, token)){
            String userId = jwtTokenProvider.getUserIdFromJWT(token);
            CustomUserDetail userDetail = userDetailsService.loadUserByUsername(userId);
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(
                    userDetail, null,userDetail.getAuthorities()
            );
            logger.info("필터 수행 유저 NO : " + userDetail.getUserNo());
            logger.info("필터 수행 유저 ID : " + userDetail.getUserId());
            logger.info("필터 수행 유저 RANK : " + userDetail.getAuthorities());

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

    private boolean validateToken(HttpServletResponse response, String token) throws IOException {
        try{
            Jwts.parser().verifyWith(jwtTokenProvider.getSecretKey()).build().parseSignedClaims(token);
            return true;
        }catch (SecurityException | MalformedJwtException e) {
            jwtErrorResponse.setErrorResponse(response, ErrorCode.INVALID_TOKEN);
            /* 글로벌 단위에서 캐치 하는 중*/
        } catch (ExpiredJwtException e) {
            jwtErrorResponse.setErrorResponse(response, ErrorCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException | IllegalArgumentException e) {
            jwtErrorResponse.setErrorResponse(response, ErrorCode.VALIDATION_ERROR);
        }
        return false;
    }
}
