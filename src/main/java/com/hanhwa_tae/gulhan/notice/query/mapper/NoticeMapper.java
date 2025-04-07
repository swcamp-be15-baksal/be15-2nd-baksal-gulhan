package com.hanhwa_tae.gulhan.notice.query.mapper;

import com.hanhwa_tae.gulhan.notice.query.dto.request.NoticeSearchRequest;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> selectNotices(NoticeSearchRequest request);
    long countPosts(NoticeSearchRequest request);

    NoticeDTO selectDetailNotice(int noticeId);
}
