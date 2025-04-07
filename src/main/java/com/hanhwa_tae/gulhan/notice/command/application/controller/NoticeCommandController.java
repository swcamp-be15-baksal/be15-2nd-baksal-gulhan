package com.hanhwa_tae.gulhan.notice.command.application.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeInsertRequest;
import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeUpdateRequest;
import com.hanhwa_tae.gulhan.notice.command.application.dto.response.NoticeCommandResponse;
import com.hanhwa_tae.gulhan.notice.command.application.service.NoticeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeCommandController {

    private final NoticeCommandService noticeCommandService;

    /* 공지사항 작성 */
    @PostMapping("/list")
    public ResponseEntity<ApiResponse<NoticeCommandResponse>> createNotice(@RequestBody NoticeInsertRequest request){
        Long noticeId = noticeCommandService.createNotice(request);

        NoticeCommandResponse response =NoticeCommandResponse.builder()
                .noticeId(noticeId)
                .build();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(response));
    }

    @PutMapping("/list/{noticeId}")
    public ResponseEntity<ApiResponse<Void>> updateNotice(@PathVariable Long noticeId, @RequestBody @Validated NoticeUpdateRequest request){
        noticeCommandService.updateNotice(noticeId,request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/list/{noticeId}")
    public ResponseEntity<ApiResponse<Void>> deleteNotice(@PathVariable Long noticeId){
        noticeCommandService.deleteNotice(noticeId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
