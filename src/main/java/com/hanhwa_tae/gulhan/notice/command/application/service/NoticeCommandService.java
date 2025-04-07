package com.hanhwa_tae.gulhan.notice.command.application.service;

import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeInsertRequest;
import com.hanhwa_tae.gulhan.notice.command.application.dto.request.NoticeUpdateRequest;
import com.hanhwa_tae.gulhan.notice.command.domain.aggregate.Notice;
import com.hanhwa_tae.gulhan.notice.command.domain.repository.NoticeRepository;
import com.hanhwa_tae.gulhan.notice.command.domain.repository.UserRepository;
import com.hanhwa_tae.gulhan.user.command.domain.aggregate.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeCommandService {

    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    /* 공지사항 등록 */
    public Long createNotice(NoticeInsertRequest request) {

        User user = userRepository.findById(request.getUserNo())
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        if(user.getRankId() != 5){
            throw new RuntimeException("관리자만 등록가능");
        }

        Notice newNotice = modelMapper.map(request, Notice.class);
        newNotice.setUserNo(user);

        Notice notice = noticeRepository.save(newNotice);

        return notice.getNoticeId();
    }

    /* 공지사항 수정 */
    @Transactional
    public void updateNotice(Long noticeId, NoticeUpdateRequest request) {
        User user = userRepository.findById(request.getUserNo())
                .orElseThrow(() -> new RuntimeException("사용자없음"));
        if(user.getRankId() != 5){
            throw new RuntimeException("관리자만 등록가능");
        }

        Notice notice =noticeRepository.findById(noticeId)
                        .orElseThrow(() -> new RuntimeException("게시글 없음"));

        notice.updateNotice(
                request.getTitle(),
                request.getContent(),
                user
        );
    }

    public void deleteNotice(Long noticeId) {
        // 받아올 방법이 토큰 넘기는거말곤 없을듯..?
//        User user = userRepository.findById(userNo)
//                .orElseThrow(() -> new RuntimeException("사용자없음"));
//        if(user.getRankId() != 5){
//            throw new RuntimeException("관리자만 등록가능");
//        }

        noticeRepository.deleteById(noticeId);
    }
}
