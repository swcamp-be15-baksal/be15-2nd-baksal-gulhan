package com.hanhwa_tae.secondserver.notice.query.service;

import com.hanhwa_tae.secondserver.common.dto.Pagination;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.notice.query.dto.request.NoticeSearchRequest;
import com.hanhwa_tae.secondserver.notice.query.dto.response.NoticeDetailDTO;
import com.hanhwa_tae.secondserver.notice.query.dto.response.NoticeDetailResponse;
import com.hanhwa_tae.secondserver.notice.query.dto.response.NoticeListDTO;
import com.hanhwa_tae.secondserver.notice.query.dto.response.NoticeListResponse;
import com.hanhwa_tae.secondserver.notice.query.mapper.NoticeMapper;
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

        List<NoticeListDTO> noticeDTOList = noticeMapper.selectNotices(request);
        long totalPosts = noticeMapper.countPosts(request);

        int page = request.getPage();
        int size = request.getSize();

        return NoticeListResponse.builder()
                .tmpList(noticeDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .size(size)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .build())
                .build();
    }

    /* 공지사항 상세 조회 */
    public NoticeDetailResponse getNoticeList(int noticeId) {
        NoticeDetailDTO noticeDTO = Optional.ofNullable(
                        noticeMapper.selectDetailNotice(noticeId))
                .orElseThrow( () -> new BusinessException(ErrorCode.NOTICE_NOT_FOUND));

        return NoticeDetailResponse.builder()
                .noticeDTO(noticeDTO)
                .build();
    }

}
