package com.hanhwa_tae.firstserver.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

// 토스페이먼츠 활용 시 필요한 키 값들 설정
@Configuration
@Getter
public class TossPaymentConfig {


    @Value("${payment.toss.test_client_api_key}")
    private String testClientApiKey;

    @Value("${payment.toss.test_secrete_api_key}")
    private String testSecretApiKey;

    @Value("${payment.toss.success_url}")
    private String successUrl;

    @Value("${payment.toss.fail_url}")
    private String failUrl;

    /*토스 요청 URL*/
    public static final String URL = "https://api.tosspayments.com/v1/payments";

}
