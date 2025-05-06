package com.hanhwa_tae.secondserver.notice.command.application.service;

import com.hanhwa_tae.secondserver.common.domain.DeleteType;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.image.dto.request.SaveImageRequest;
import com.hanhwa_tae.secondserver.image.service.ImageService;
import com.hanhwa_tae.secondserver.notice.command.application.dto.request.NoticeInsertRequest;
import com.hanhwa_tae.secondserver.notice.command.application.dto.request.NoticeUpdateRequest;
import com.hanhwa_tae.secondserver.notice.command.domain.aggregate.Notice;
import com.hanhwa_tae.secondserver.notice.command.domain.repository.JpaNoticeRepository;
import com.hanhwa_tae.secondserver.user.command.domain.aggregate.User;
import com.hanhwa_tae.secondserver.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeCommandService {

    private final JpaNoticeRepository jpaNoticeRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final ImageService imageService;

    /* 공지사항 등록 */
    public Long createNotice(String id, NoticeInsertRequest request) {

        User user = userMapper.findUserByUserId(id)
                .orElseThrow( () -> new BusinessException(ErrorCode.ADMIN_NOT_MATCHING));

        Notice newNotice = modelMapper.map(request, Notice.class);
        newNotice.setUser(user);

        List<String> imageUrls = request.getImageUrls();

        if(!imageUrls.isEmpty()){
            imageService.saveImage(SaveImageRequest.builder().imageList(imageUrls).build());
        }

        Notice notice = jpaNoticeRepository.save(newNotice);

        return notice.getNoticeId();
    }

    /* 공지사항 수정 - 수정한 관리자 id로 바꿈 */
    @Transactional
    public void updateNotice(String id, Long noticeId, NoticeUpdateRequest request) {

        User user = userMapper.findUserByUserId(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ADMIN_NOT_MATCHING));

        Notice notice = jpaNoticeRepository.findById(noticeId)
                        .orElseThrow(() -> new BusinessException(ErrorCode.NOTICE_NOT_FOUND));

        List<String> imageUrls = request.getImageUrls();

        if(!imageUrls.isEmpty()){
            imageService.saveImage(SaveImageRequest.builder().imageList(imageUrls).build());
        }

        notice.updateNotice(
                request.getTitle(),
                request.getContent(),
                user
        );
    }

    /* 공지사항 삭제 - soft delete */
    @Transactional
    public void deleteNotice(Long noticeId) {

        Notice notice = jpaNoticeRepository.findById(noticeId)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOTICE_NOT_FOUND));

        if(!notice.getIsDeleted().equals(DeleteType.N)) {
            throw new BusinessException(ErrorCode.NOTICE_NOT_FOUND);
        }

        jpaNoticeRepository.deleteById(noticeId);
    }
}
