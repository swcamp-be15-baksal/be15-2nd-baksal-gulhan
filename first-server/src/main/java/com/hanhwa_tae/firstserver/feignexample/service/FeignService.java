package com.hanhwa_tae.firstserver.feignexample.service;

import com.hanhwa_tae.firstserver.client.UserClient;
import com.hanhwa_tae.firstserver.feignexample.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeignService {
    private final UserClient userClient;

    public void getUserInfo() {
        UserInfoResponse response = userClient.getUserInfo().getData();
        log.info(response.toString());
    }
}
