package com.hanhwa_tae.secondserver.security;

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
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 처리 비활성화
        http
//                .cors(cors -> cors
//                        .configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                // 세션 로그인 x -> 토큰 로그인 설정으로 진행한다
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler))
                // 요청 http method, url 기준으로 인증, 인가 필요 여부 설정
                .authorizeHttpRequests(auth ->
                        auth
                                /* swagger */
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                                /*유저 권한*/
                                .requestMatchers(HttpMethod.GET,
                                        "/board/list",
                                        "/packages/**",
                                        "/goods/**",
                                        "/review",
                                        "/oauth/kakao/login",
                                        "/oauth/kakao/callback",
                                        "/place/places",
                                        "/place/placeDetail/**",
                                        "/areas/list",
                                        "/board/list",
                                        "/users/ranks",
                                        "/delivery"
                                ).permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "auth/login",
                                        "auth/token/reissue",
                                        "users/register",
                                        "users/find/password",
                                        "users/find/id"
                                ).permitAll()
                                /* 관리자 권한*/
                                .requestMatchers(HttpMethod.GET,
                                        "/admin/**"
                                ).hasAuthority("SLAVE")

                                .requestMatchers(HttpMethod.POST,
                                        "/admin/**"
                                        , "/notice/**"
                                        , "/packages"
                                        , "/goods"
                                ).hasAuthority("SLAVE")

                                .requestMatchers(HttpMethod.PUT,
                                        "/admin/**"
                                        , "/notice/**"
                                        , "/packages/**"
                                        , "/goods/**"
                                ).hasAuthority("SLAVE")

                                .requestMatchers(HttpMethod.DELETE,
                                        "/admin/**"
                                        , "/notice/**"
                                        , "/packages/**"
                                        , "/goods/**"
                                ).hasAuthority("SLAVE")

                                /* 회원 권한 */
                                .requestMatchers(HttpMethod.GET,
                                        "/notice/**"
                                        , "/like/likes"
                                        , "/comment"
                                        , "/board/list/**"
                                        , "/users/withdraw"
                                        , "/users/info"
                                ).authenticated()

                                .requestMatchers(HttpMethod.POST,
                                        "/admin/**"
                                        , "/review"
                                        , "/like/toggle"
                                        , "/comment"
                                        , "/board"
                                        , "/auth/logout"
                                ).authenticated()

                                .requestMatchers(HttpMethod.PUT,
                                        "/admin/**"
                                        , "/review/**"
                                        , "/comment"
                                        , "/board/list/**"
                                        , "/users/info/update"
                                        , "/users/change/password"
                                ).authenticated()

                                .requestMatchers(HttpMethod.DELETE,
                                        "/admin/**"
                                        , "/review/**"
                                        , "/comment"
                                        , "/board/list/**"
                                ).authenticated()

                                .requestMatchers(
                                        "/oauth/kakao/**"
                                ).permitAll()
                                .anyRequest().permitAll()   // 테스트 땜에 열어


                ).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        return new CorsFilter(corsConfigurationSource());
//    }

//    @Bean
//    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:5173"); // 허용할 도메인
//        config.addAllowedOrigin("http://localhost:8000");
//        config.addAllowedHeader("*"); // 모든 헤더 허용
//        config.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
}

