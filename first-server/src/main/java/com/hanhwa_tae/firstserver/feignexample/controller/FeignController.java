package com.hanhwa_tae.firstserver.feignexample.controller;


import com.hanhwa_tae.firstserver.common.dto.ApiResponse;
import com.hanhwa_tae.firstserver.feignexample.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class FeignController {

    private final FeignService feignService;

    @GetMapping("/user-info")
    public ResponseEntity<ApiResponse<Void>> getUserInfo(){
        feignService.getUserInfo();

        return ResponseEntity.ok(ApiResponse.success(null));

    }
}
