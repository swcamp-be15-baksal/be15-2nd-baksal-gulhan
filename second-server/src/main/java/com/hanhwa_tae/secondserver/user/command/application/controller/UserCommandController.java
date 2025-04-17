package com.hanhwa_tae.secondserver.user.command.application.controller;

import com.hanhwa_tae.secondserver.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.user.command.application.dto.request.*;
import com.hanhwa_tae.secondserver.user.command.application.dto.response.UserFindIdResponse;
import com.hanhwa_tae.secondserver.user.command.application.service.UserCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserCommandController {

    private final UserCommandService userCommandService;

    @Operation(summary = "회원 가입", description = "회원 가입을 위한 정보를 입력하고 이메일 인증을 요청한다. ")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(
            @RequestBody @Valid UserCreateRequest request
    ) throws MessagingException {
        userCommandService.registerUser(request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    // 이메일 인증
    /* 사용자가 전송한 내용을 검증해야 함*/
    @Operation(summary = "이메일 인증", description = "이메일 인증을 통해 회원 가입을 완료한다.")
    @GetMapping("/verify-email")
    public ResponseEntity<ApiResponse<Void>> verifyEmail(
            @RequestParam(required = true) String uuid
    ){
        userCommandService.verifyByEmail(uuid);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "회원 정보 변경", description = "회원 정보를 수정한다.")
    @PutMapping("/info/update")
    public ResponseEntity<ApiResponse<Void>> updateUserInfo(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody @Valid UpdateUserInfoRequest request
            ){

        userCommandService.updateUserInfo(userDetail, request);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "비밀번호 찾기", description = "임시 비밀번호를 입력한 이메일로 전송한다.")
    @PostMapping("/find/password")
    public ResponseEntity<ApiResponse<Void>> findUserPassword(
            @RequestBody @Valid UserFindPasswordRequest request
    ) throws MessagingException {
        userCommandService.findUserPassword(request);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "아이디 찾기", description = "아이디 찾기를 위한 이메일 인증 요청을 보낸다.")
    @PostMapping("/find/id")
    public ResponseEntity<ApiResponse<Void>> findUserId(
            @RequestBody UserFindIdRequest request
    ) throws MessagingException {
        userCommandService.findUserId(request);

        /* 이메일 인증 요청 */
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "아이디 찾기", description = "이메일 인증을 통해 마스킹된 아이디를 확인한다.")
    @GetMapping("/find/id/verify")
    public ResponseEntity<ApiResponse<UserFindIdResponse>> verifyFindUserId(
            @RequestParam(required = true) String uuid
    ) {
        String maskedUserId = userCommandService.verifyFindUserId(uuid);

        /* 이메일 인증 요청 */
        return ResponseEntity.ok(
                ApiResponse.success(
                        UserFindIdResponse.builder().maskedUserId(maskedUserId).build()));
    }

    @Operation(summary = "비밀번호 변경", description = "회원은 자신의 비밀번호를 변경할 수 있다.")
    @PutMapping("/change/password")
    public ResponseEntity<ApiResponse<Void>> changeUserPassword(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody @Valid ChangeUserPasswordRequest request
    ){
        userCommandService.changeUserPassword(userDetail, request);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "회원 탈퇴", description = "회원은 회원 탈퇴 요청을 할 수 있다.")
    @GetMapping("/withdraw")
    public ResponseEntity<ApiResponse<Void>> withdrawUser(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ){
        userCommandService.withdrawUser(userDetail);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "배송지 등록", description = "회원은 배송지를 등록할 수 있다.")
    @PostMapping("/delivery-address/register")
    public ResponseEntity<ApiResponse<Void>> registerDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody DeliveryAddressRequest request
    ) {
        String userId = userDetail.getUserId();
        userCommandService.registerDeliveryAddress(userId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "배송지 수정", description = "회원은 등록한 배송지를 수정할 수 있다.")
    @PutMapping("/delivery-address/update")
    public ResponseEntity<ApiResponse<Void>> updateDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody DeliveryAddressRequest request
    ) {
        String userId = userDetail.getUserId();
        userCommandService.updateDeliveryAddress(userId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "배송지 삭제", description = "회원은 등록한 배송지를 삭제할 수 있다.")
    @PostMapping("/delivery-address/delete")
    public ResponseEntity<ApiResponse<Void>> deleteDeliveryAddress(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        String userId = userDetail.getUserId();
        userCommandService.deleteDeliveryAddress(userId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
