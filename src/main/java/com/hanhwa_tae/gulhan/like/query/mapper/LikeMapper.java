package com.hanhwa_tae.gulhan.like.query.mapper;

import com.hanhwa_tae.gulhan.like.query.dto.request.LikeSearchRequest;
import com.hanhwa_tae.gulhan.like.query.dto.response.LikeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    List<LikeDto> selectLikes(String userId, LikeSearchRequest req);

    long countLikes(String userId, LikeSearchRequest req);
}
