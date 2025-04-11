package com.hanhwa_tae.gulhan.config;

import com.hanhwa_tae.gulhan.auth.command.application.service.CustomUserDetailService;
import com.hanhwa_tae.gulhan.utils.jwt.CustomAccessDeniedHandler;
import com.hanhwa_tae.gulhan.utils.jwt.CustomAuthenticationEntryPoint;
import com.hanhwa_tae.gulhan.utils.jwt.JwtAuthenticationFilter;
import com.hanhwa_tae.gulhan.utils.jwt.JwtTokenProvider;
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
//                                .requestMatchers(HttpMethod.POST, "/api/v1/users/register", "/api/v1/auth/refresh").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/api/v1/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/packages").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/packages/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/packages/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/packages/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/packages").permitAll()
//                                .requestMatchers(HttpMethod.GET,  "/api/v1/users/verify-email").permitAll()
//                                .requestMatchers(HttpMethod.GET, "/api/v1/users/me").hasAuthority("USER")
//                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtTokenProvider, userDetailsService);
    }
}

