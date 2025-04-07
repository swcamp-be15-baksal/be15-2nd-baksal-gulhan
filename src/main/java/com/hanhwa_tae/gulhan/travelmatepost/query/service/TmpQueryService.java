package com.hanhwa_tae.gulhan.travelmatepost.query.service;

import com.hanhwa_tae.gulhan.common.dto.Pagination;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.request.TmpSearchRequest;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpDTO;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpDetailResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpListResponse;
import com.hanhwa_tae.gulhan.travelmatepost.query.mapper.TmpMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TmpQueryService {

    private final TmpMapper tmpMapper;

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
                        .totalSize((int) Math.ceil((double) totalPosts / size))
                        .totalPosts(totalPosts)
                        .build())
                .build();
    }

    /* 동행글 상세 조회 */
    public TmpDetailResponse getTmpList(int travelMatePostId) {

        TmpDTO tmp = Optional.ofNullable(
                tmpMapper.selectTmpByTravelMatePostId(travelMatePostId))
                .orElseThrow(BuilderException::new);

        return TmpDetailResponse.builder()
                .tmpDTO(tmp)
                .build();
    }
}
