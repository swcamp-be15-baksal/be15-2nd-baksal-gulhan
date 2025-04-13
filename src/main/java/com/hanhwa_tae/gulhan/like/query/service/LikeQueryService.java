package com.hanhwa_tae.gulhan.like.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.like.query.dto.request.LikeSearchRequest;
import com.hanhwa_tae.gulhan.like.query.dto.response.LikeDto;
import com.hanhwa_tae.gulhan.like.query.dto.response.LikeListResponse;
import com.hanhwa_tae.gulhan.like.query.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeQueryService {
    private final LikeMapper likeMapper;

    @Transactional(readOnly = true)
    public LikeListResponse getLikes(String userId, LikeSearchRequest likeSearchRequest) {

        List<LikeDto> likes = likeMapper.selectLikes(userId, likeSearchRequest);

        long totalLikes = likeMapper.countLikes(userId, likeSearchRequest);

        int page = likeSearchRequest.getPage();
        int size = likeSearchRequest.getSize();

        return LikeListResponse.builder()
                .likes(likes)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int)Math.ceil((double)totalLikes / size))
                        .size(size)
                        .build())
                .build();
    }
}
