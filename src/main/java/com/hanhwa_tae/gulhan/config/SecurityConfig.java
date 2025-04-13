package com.hanhwa_tae.gulhan.config;

import com.hanhwa_tae.gulhan.auth.command.application.service.CustomUserDetailService;
import com.hanhwa_tae.gulhan.utils.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailService userDetailsService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtErrorResponse jwtErrorResponse;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 처리 비활성화
        http.csrf(AbstractHttpConfigurer::disable)
                // 세션 로그인 x -> 토큰 로그인 설정으로 진행한다
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler))
                // 요청 http method, url 기준으로 인증, 인가 필요 여부 설정
                .authorizeHttpRequests(auth ->
                                auth
                                        /* 관리자 권한*/
                                        .requestMatchers(HttpMethod.GET,
                                                "/api/v1/admin/**"
                                        ).hasAuthority("SLAVE")

                                        .requestMatchers(HttpMethod.POST,
                                                "/api/v1/admin/**"
                                                , "/api/v1/notice/**"
                                                , "/api/v1/packages"
                                                , "/api/v1/goods"
                                        ).hasAuthority("SLAVE")

                                        .requestMatchers(HttpMethod.PUT,
                                                "/api/v1/admin/**"
                                                , "/api/v1/notice/**"
                                                , "/api/v1/packages/**"
                                                , "/api/v1/goods/**"
                                        ).hasAuthority("SLAVE")

                                        .requestMatchers(HttpMethod.DELETE,
                                                "/api/v1/admin/**"
                                                , "/api/v1/notice/**"
                                                , "/api/v1/packages/**"
                                                , "/api/v1/goods/**"
                                        ).hasAuthority("SLAVE")

                                        /* 회원 권한 */
                                        .requestMatchers(HttpMethod.GET,
                                                "/api/v1/notice/**"
                                                , "/api/v1/like/likes"
                                                , "/api/v1/comment"
                                                , "/api/v1/board/list/**"
                                                , "/api/v1/users/withdraw"
                                                , "/api/v1/users/info"
                                        ).authenticated()

                                        .requestMatchers(HttpMethod.POST,
                                                "/api/v1/admin/**"
                                                , "/api/v1/review"
                                                , "/api/v1/oauth/kakao/**"
                                                , "/api/v1/like/toggle"
                                                , "/api/v1/comment"
                                                , "/api/v1/board"
                                                , "/api/v1/auth/logout"
                                        ).authenticated()

                                        .requestMatchers(HttpMethod.PUT,
                                                "/api/v1/admin/**"
                                                , "/api/v1/review/**"
                                                , "/api/v1/comment"
                                                , "/api/v1/board/list/**"
                                                , "/api/v1/users/info/update"
                                                , "/api/v1/users/change/password"
                                        ).authenticated()

                                        .requestMatchers(HttpMethod.DELETE,
                                                "/api/v1/admin/**"
                                                , "/api/v1/review/**"
                                                , "/api/v1/comment"
                                                , "/api/v1/board/list/**"
                                        ).authenticated()
                                        .anyRequest().permitAll()   // 테스트 땜에 열어

                ).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);
    }


}

