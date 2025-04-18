package com.hanhwa_tae.firstserver.like.command.application.service;

import com.hanhwa_tae.firstserver.common.exception.BusinessException;
import com.hanhwa_tae.firstserver.common.exception.ErrorCode;
import com.hanhwa_tae.firstserver.goods.command.domain.repository.JpaGoodsRepository;
import com.hanhwa_tae.firstserver.like.command.application.dto.request.LikeCreateRequest;
import com.hanhwa_tae.firstserver.like.command.domain.aggregate.Like;
import com.hanhwa_tae.firstserver.like.command.domain.repository.LikeRepository;
import com.hanhwa_tae.firstserver.packages.command.domain.repository.JpaPackageRepository;
import com.hanhwa_tae.firstserver.place.command.domain.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeCommandService {

    private final LikeRepository likeRepository;
    private final JpaGoodsRepository jpaGoodsRepository;
    private final PlaceRepository placeRepository;
    private final JpaPackageRepository jpaPackageRepository;

    @Transactional
    public boolean toggleLike(String userId, LikeCreateRequest request) {
        Optional<Like> existingLike = likeRepository.findByTargetIdAndTargetTypeAndUserId(
                request.getTargetId(), request.getTargetType(), userId
        );
        boolean exists = switch (request.getTargetType()) {
            case PLACE -> placeRepository.existsById(request.getTargetId());
            case GOODS -> jpaGoodsRepository.existsById(request.getTargetId());
            case PACKAGE -> jpaPackageRepository.existsById(request.getTargetId());
        };

        if (!exists) {
            throw new BusinessException(ErrorCode.TARGET_NOT_FOUND);
        }

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
