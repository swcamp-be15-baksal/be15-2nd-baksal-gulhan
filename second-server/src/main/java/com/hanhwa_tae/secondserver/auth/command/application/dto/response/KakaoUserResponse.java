package com.hanhwa_tae.secondserver.auth.command.application.dto.response;

import lombok.*;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class KakaoUserResponse {
    private Long id;
    private Map<String, Object> properties;
    private Map<String, Object> kakaoAccount;
}


