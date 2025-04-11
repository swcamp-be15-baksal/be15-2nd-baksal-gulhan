package com.hanhwa_tae.gulhan.payment.command.application.controller;

import com.hanhwa_tae.gulhan.config.TossPaymentConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class GetKeyController {
    private final TossPaymentConfig tossPaymentConfig;

    @GetMapping("/key")
    public Map<String, String> getKeyValue() {
        Map<String, String> result = new HashMap<>();
        String value = tossPaymentConfig.getTestClientApiKey();
        result.put("keyValue", value);
        return result;
    }
}
