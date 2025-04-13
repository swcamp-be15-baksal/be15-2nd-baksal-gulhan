package com.hanhwa_tae.gulhan.notice.command.application.controller;

import com.hanhwa_tae.gulhan.auth.command.domain.aggregate.model.CustomUserDetail;
import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeInsertRequest;
import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeUpdateRequest;
import com.hanhwa_tae.gulhan.notice.command.application.dto.response.NoticeCommandResponse;
import com.hanhwa_tae.gulhan.notice.command.application.service.NoticeCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공지사항")
@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeCommandController {

    private final NoticeCommandService noticeCommandService;

    /* 공지사항 작성 */
    @Operation(summary = "공지사항 작성", description = "공지사항을 작성한다. (관리자만)")
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<NoticeCommandResponse>> createNotice(
            @AuthenticationPrincipal CustomUserDetail  customUserDetail,
            @RequestBody NoticeInsertRequest request){

        String id =  customUserDetail.getUsername();

        Long noticeId = noticeCommandService.createNotice(id, request);

        NoticeCommandResponse response = NoticeCommandResponse.builder()
                .noticeId(noticeId)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    /* 공지사항 수정 */
    @Operation(summary = "공지사항 수정", description = "공지사항을 수정한다. (관리자만)")
    @PutMapping("/list/{noticeId}")
    public ResponseEntity<ApiResponse<Void>> updateNotice(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Long noticeId,
            @RequestBody @Validated NoticeUpdateRequest request
    ) {
        String id = customUserDetail.getUsername();
        noticeCommandService.updateNotice(id, noticeId,request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 공지사항 삭제 */
    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제한다. (관리자만)")
    @DeleteMapping("/list/{noticeId}")
    public ResponseEntity<ApiResponse<Void>> deleteNotice( @PathVariable Long noticeId){
        noticeCommandService.deleteNotice(noticeId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
