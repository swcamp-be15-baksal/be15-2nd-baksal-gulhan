package com.hanhwa_tae.gulhan.notice.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.notice.query.dto.request.NoticeSearchRequest;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeDTO;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeDetailResponse;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeListResponse;
import com.hanhwa_tae.gulhan.notice.query.mapper.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeQueryService {

    private final NoticeMapper noticeMapper;

    /* 공지사항 목록 조회 */
    public NoticeListResponse getNoticeList(NoticeSearchRequest request) {

        List<NoticeDTO> noticeDTOList = noticeMapper.selectNotices(request);
        long totalPosts = noticeMapper.countPosts(request);

        int page = request.getPage();
        int size = request.getSize();

        return NoticeListResponse.builder()
                .tmpList(noticeDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalSize((int) Math.ceil((double) totalPosts / size))
                        .totalPosts(totalPosts)
                        .build())
                .build();
    }

    /* 공지사항 상세 조회 */
    public NoticeDetailResponse getNoticeList(int noticeId) {
        NoticeDTO noticeDTO = Optional.ofNullable(
                        noticeMapper.selectDetailNotice(noticeId))
                .orElseThrow(RuntimeException::new);

        return NoticeDetailResponse.builder()
                .noticeDTO(noticeDTO)
                .build();
    }

}
