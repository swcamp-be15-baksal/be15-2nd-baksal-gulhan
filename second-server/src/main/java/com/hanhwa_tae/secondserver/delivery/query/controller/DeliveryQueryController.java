package com.hanhwa_tae.secondserver.delivery.query.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.delivery.command.application.dto.request.DeliveryAddressRequest;
import com.hanhwa_tae.secondserver.delivery.query.dto.response.DeliveryAddressResponse;
import com.hanhwa_tae.secondserver.delivery.query.dto.request.DeliveryInfoRequest;
import com.hanhwa_tae.secondserver.delivery.query.service.DeliveryQueryService;
import com.hanhwa_tae.secondserver.user.command.application.dto.response.DeliveryStateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryQueryController {

    private final DeliveryQueryService deliveryQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<DeliveryStateResponse>> getDeliveryInfo(
            DeliveryInfoRequest request
    ){
        DeliveryStateResponse response = deliveryQueryService.getDeliveryInfo(request);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

}
