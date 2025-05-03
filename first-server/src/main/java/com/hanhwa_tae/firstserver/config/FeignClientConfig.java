package com.hanhwa_tae.firstserver.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {

            ServletRequestAttributes requestAttributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

            if(requestAttributes != null){
                String userNo = requestAttributes.getRequest().getHeader("X-User-No");
                String userId = requestAttributes.getRequest().getHeader("X-User-Id");
                String userRank = requestAttributes.getRequest().getHeader("X-User-Rank");
                requestTemplate.header("X-User-No", userNo);
                requestTemplate.header("X-User-Id", userId);
                requestTemplate.header("X-User-Rank", userRank);

            }
        };
    }
}
