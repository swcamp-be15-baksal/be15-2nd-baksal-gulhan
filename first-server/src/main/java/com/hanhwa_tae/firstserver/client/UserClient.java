package com.hanhwa_tae.firstserver.client;

import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.config.FeignClientConfig;
import com.hanhwa_tae.firstserver.feignexample.dto.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-server", configuration = FeignClientConfig.class)
public interface UserClient {

    @GetMapping("/users/info")
    ApiResponse<UserInfoResponse> getUserInfo();

    @PostMapping("/image/save")
    ApiResponse<Void> saveImage(SaveImageRequest request);

}
