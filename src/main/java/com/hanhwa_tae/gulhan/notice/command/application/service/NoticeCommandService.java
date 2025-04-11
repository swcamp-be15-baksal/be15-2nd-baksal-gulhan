package com.hanhwa_tae.gulhan.notice.command.application.service;

import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeInsertRequest;
import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeUpdateRequest;
import com.hanhwa_tae.gulhan.notice.command.domain.aggregate.Notice;
import com.hanhwa_tae.gulhan.notice.command.domain.repository.JpaNoticeRepository;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import com.hanhwa_tae.gulhan.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeCommandService {

    private final JpaNoticeRepository jpaNoticeRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    /* 공지사항 등록 */
    public Long createNotice(String id, NoticeInsertRequest request) {

        User user = userMapper.findUserByUserId(id)
                .orElseThrow( () -> new RuntimeException("관리자 없음"));

        Notice newNotice = modelMapper.map(request, Notice.class);
        newNotice.setUser(user);

        Notice notice = jpaNoticeRepository.save(newNotice);

        return notice.getNoticeId();
    }

    /* 공지사항 수정 - 수정한 관리자 id로 바꿈 */
    @Transactional
    public void updateNotice(String id, Long noticeId, NoticeUpdateRequest request) {

        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new RuntimeException("사용자없음"));

        Notice notice = jpaNoticeRepository.findById(noticeId)
                        .orElseThrow(() -> new RuntimeException("게시글 없음"));

        notice.updateNotice(
                request.getTitle(),
                request.getContent(),
                user
        );
    }

    public void deleteNotice(Long noticeId) {

        jpaNoticeRepository.deleteById(noticeId);
    }
}
