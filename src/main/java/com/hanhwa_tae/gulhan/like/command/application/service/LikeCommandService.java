package com.hanhwa_tae.gulhan.like.command.application.service;

import com.hanhwa_tae.gulhan.like.command.application.dto.request.LikeCreateRequest;
import com.hanhwa_tae.gulhan.like.command.domain.aggregate.Like;
import com.hanhwa_tae.gulhan.like.command.domain.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LikeCommandService {

    private final LikeRepository likeRepository;

    public LikeCommandService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
    @Transactional
    public boolean toggleLike(String userId, LikeCreateRequest request) {
        Optional<Like> existingLike = likeRepository.findByTargetIdAndTargetTypeAndUserId(
                request.getTargetId(), request.getTargetType(), userId
        );

        if (existingLike.isPresent()) {
            likeRepository.deleteByTargetIdAndTargetTypeAndUserId(
                    request.getTargetId(), request.getTargetType(), userId
            );
            return false; // 좋아요 해제
        } else {
            Like like = new Like(
                    request.getTargetId(),
                    request.getTargetType(),
                    userId
            );
            likeRepository.save(like);
            return true; // 좋아요 추가
        }
    }
}
