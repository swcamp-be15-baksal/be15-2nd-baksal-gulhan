package com.hanhwa_tae.secondserver.delivery.command.application.controller;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.delivery.command.application.dto.request.DeliveryAddressRequest;
import com.hanhwa_tae.secondserver.delivery.command.application.service.DeliveryCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "배송지")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class DeliveryCommandController {

    private final DeliveryCommandService deliveryCommandService;

    @Operation(summary = "배송지 등록", description = "회원은 배송지를 등록할 수 있다.")
    @PostMapping("/delivery-address/register")
    public ResponseEntity<ApiResponse<Void>> registerDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody DeliveryAddressRequest request
    ) {
        String userId = userDetail.getUserId();
        deliveryCommandService.registerDeliveryAddress(userId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "배송지 수정", description = "회원은 등록한 배송지를 수정할 수 있다.")
    @PutMapping("/delivery-address/update/{deliveryAddressId}")
    public ResponseEntity<ApiResponse<Void>> updateDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable int deliveryAddressId,
            @RequestBody DeliveryAddressRequest request
    ) {
        String userId = userDetail.getUserId();
        deliveryCommandService.updateDeliveryAddress(userId, deliveryAddressId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "배송지 삭제", description = "회원은 등록한 배송지를 삭제할 수 있다.")
    @PostMapping("/delivery-address/delete/{deliveryAddressId}")
    public ResponseEntity<ApiResponse<Void>> deleteDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable int deliveryAddressId
    ) {
        String userId = userDetail.getUserId();
        deliveryCommandService.deleteDeliveryAddress(userId, deliveryAddressId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
