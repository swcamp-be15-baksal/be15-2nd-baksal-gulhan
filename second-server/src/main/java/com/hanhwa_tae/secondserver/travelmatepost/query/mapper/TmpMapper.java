package com.hanhwa_tae.secondserver.travelmatepost.query.mapper;

import com.hanhwa_tae.secondserver.travelmatepost.query.dto.request.TmpSearchRequest;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpDTO;
import com.hanhwa_tae.secondserver.travelmatepost.query.dto.response.TmpDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TmpMapper {

    TmpDetailDTO selectTmpByTravelMatePostId(Integer travelMatePostId);

    List<TmpDTO> selectTravelMatePosts(TmpSearchRequest tmpSearchRequest);

    long countPosts(TmpSearchRequest tmpSearchRequest);
}
