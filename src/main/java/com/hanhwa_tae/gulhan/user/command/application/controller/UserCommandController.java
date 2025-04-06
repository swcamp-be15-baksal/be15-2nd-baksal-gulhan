package com.hanhwa_tae.gulhan.user.command.application.controller;

import com.hanhwa_tae.gulhan.user.command.application.dto.request.UserCreateRequest;
import com.hanhwa_tae.gulhan.user.command.application.service.UserCommandService;
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
    ) {
        userCommandService.registerUser(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
//                .body(ApiResponse.success(null));
    }

    // 이메일 인증
    @GetMapping("/verify-email")
    public ResponseEntity<Void> verifyEmail(){
        /* TODO: 이메일 인증 로직 필요 */
        
        return ResponseEntity.ok(null);
    }

}
