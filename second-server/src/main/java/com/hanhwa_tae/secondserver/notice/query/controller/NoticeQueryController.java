package com.hanhwa_tae.secondserver.notice.query.controller;

import com.hanhwa_tae.secondserver.common.dto.ApiResponse;
import com.hanhwa_tae.secondserver.notice.query.dto.request.NoticeSearchRequest;
import com.hanhwa_tae.secondserver.notice.query.dto.response.NoticeDetailResponse;
import com.hanhwa_tae.secondserver.notice.query.dto.response.NoticeListResponse;
import com.hanhwa_tae.secondserver.notice.query.service.NoticeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "공지사항")
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeQueryController {

    private final NoticeQueryService noticeQueryService;

    /* 공지사항 목록 조회 */
    @Operation(summary = "공지사항 목록 조회", description = "공지사항의 전체 목록을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<NoticeListResponse>> getNoticeList(
            NoticeSearchRequest noticeSearchRequest) {
        NoticeListResponse res = noticeQueryService.getNoticeList(noticeSearchRequest);

        return ResponseEntity.ok(ApiResponse.success(res));
    }

    /* 공지사항 상세 조회 */
    @Operation(summary = "공지사항 상세 조회", description = "공지사항을 상세 조회한다.")
    @GetMapping("/list/{noticeId}")
    public ResponseEntity<ApiResponse<NoticeDetailResponse>> getNoticeDetail(@PathVariable int noticeId) {
        NoticeDetailResponse res = noticeQueryService.getNoticeList(noticeId);

        return ResponseEntity.ok(ApiResponse.success(res));
    }

}
