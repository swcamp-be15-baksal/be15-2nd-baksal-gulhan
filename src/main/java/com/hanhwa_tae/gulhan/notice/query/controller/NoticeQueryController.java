package com.hanhwa_tae.gulhan.notice.query.controller;

import com.hanhwa_tae.gulhan.common.dto.ApiResponse;
import com.hanhwa_tae.gulhan.notice.query.dto.request.NoticeSearchRequest;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeDetailResponse;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeListResponse;
import com.hanhwa_tae.gulhan.notice.query.service.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeQueryController {

    private final NoticeQueryService noticeQueryService;

    /* 공지사항 목록 조회 */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<NoticeListResponse>> getNoticeList(
            NoticeSearchRequest noticeSearchRequest) {
        NoticeListResponse res = noticeQueryService.getNoticeList(noticeSearchRequest);

        return ResponseEntity.ok(ApiResponse.success(res));
    }

    /* 공지사항 상세 조회 */
    @GetMapping("/list/{noticeId}")
    public ResponseEntity<ApiResponse<NoticeDetailResponse>> getNoticeDetail(@PathVariable int noticeId) {
        NoticeDetailResponse res = noticeQueryService.getNoticeList(noticeId);

        return ResponseEntity.ok(ApiResponse.success(res));
    }

}
