package com.hanhwa_tae.gulhan.notice.query.mapper;

import com.hanhwa_tae.gulhan.notice.query.dto.request.NoticeSearchRequest;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeDetailDTO;
import com.hanhwa_tae.gulhan.notice.query.dto.response.NoticeListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeListDTO> selectNotices(NoticeSearchRequest request);
    long countPosts(NoticeSearchRequest request);

    NoticeDetailDTO selectDetailNotice(int noticeId);
}
