package com.hanhwa_tae.secondserver.travelmatepost.query.service;

import com.hanhwa_tae.secondserver.common.dto.Pagination;
import com.hanhwa_tae.secondserver.common.exception.BusinessException;
import com.hanhwa_tae.secondserver.common.exception.ErrorCode;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.request.TmpSearchRequest;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpDTO;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpDetailDTO;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpDetailResponse;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpListResponse;
import com.hanhwa_tae.secondserver.travelmatepost.query.mapper.CommentMapper;
import com.hanhwa_tae.secondserver.travelmatepost.query.mapper.TmpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TmpQueryService {

    private final TmpMapper tmpMapper;
    private final CommentMapper commentMapper;

    /* 게시판 목록 조회 */
    public TmpListResponse getTmpList(TmpSearchRequest tmpSearchRequest) {

        List<TmpDTO> tmpDTOList = tmpMapper.selectTravelMatePosts(tmpSearchRequest);

        long totalPosts = tmpMapper.countPosts(tmpSearchRequest);

        int page = tmpSearchRequest.getPage();
        int size = tmpSearchRequest.getSize();

        return TmpListResponse.builder()
                .tmpList(tmpDTOList)
                .pagination(Pagination.builder()
                        .currentPage(page)
                        .totalPage((int) Math.ceil((double) totalPosts / size))
                        .size(size)
                        .build())
                .build();
    }

    /* 동행글 상세 조회 */
    public TmpDetailResponse getTmpList(int travelMatePostId) {

        TmpDetailDTO tmp = Optional.ofNullable(
                tmpMapper.selectTmpByTravelMatePostId(travelMatePostId))
                .orElseThrow(() -> new BusinessException(ErrorCode.POST_NOT_FOUND));

        return TmpDetailResponse.builder()
                .tmpDetailDTO(tmp)
                .build();
    }

}
