package com.hanhwa_tae.gulhan.user.command.application.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserFindIdRequest;
import com.hanhwa_tae.gulhan.user.command.application.service.UserCommandService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserCommandController {

    private final UserCommandService userCommandService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody @Valid UserCreateRequest request
    ) throws MessagingException {
        userCommandService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
//                .body(ApiResponse.success(null));
    }

    // 이메일 인증
    /* 사용자가 전송한 내용을 검증해야 함*/
    @GetMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(
            @RequestParam(required = true) String uuid
    ){
        userCommandService.verifyByEmail(uuid);

        return ResponseEntity.ok(null);
    }

    @PostMapping("/find/id")
    public ResponseEntity<ApiResponse<Void>> findUserId(
            @RequestBody UserFindIdRequest request
    ) throws MessagingException {
        userCommandService.findUserId(request);

        /* 이메일 인증 요청 */
        return ResponseEntity.ok(null);
    }

    @GetMapping("/find/id/verify")
    public ResponseEntity<ApiResponse<Void>> verifyFindUserId(
            @RequestParam(required = true) String uuid
    )  {
        userCommandService.verifyFindUserId(uuid);

        /* 이메일 인증 요청 */
        return ResponseEntity.ok(null);
    }


//    @PostMapping("/find/password")


}
