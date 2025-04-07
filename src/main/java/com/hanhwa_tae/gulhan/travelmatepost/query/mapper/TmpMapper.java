package com.hanhwa_tae.gulhan.travelmatepost.query.mapper;

import com.hanhwa_tae.gulhan.travelmatepost.query.dto.request.TmpSearchRequest;
import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.TmpDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TmpMapper {

    TmpDTO selectTmpByTravelMatePostId(Integer travelMatePostId);

    List<TmpDTO> selectTravelMatePosts(TmpSearchRequest tmpSearchRequest);

    long countPosts(TmpSearchRequest tmpSearchRequest);
}
