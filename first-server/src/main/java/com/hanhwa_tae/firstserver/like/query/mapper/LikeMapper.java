package com.hanhwa_tae.firstserver.like.query.mapper;


import com.hanhwa_tae.firstserver.like.query.dto.request.LikeSearchRequest;
import com.hanhwa_tae.firstserver.like.query.dto.response.LikeDto;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LikeMapper {
    List<LikeDto> selectLikes(String userId, LikeSearchRequest req);

    boolean existsLike(
            @Param("userId") String userId,
            @Param("targetId") int targetId,
            @Param("targetType") String targetType
    );

    long countLikes(String userId, LikeSearchRequest req);

    long countByTarget(
            @Param("targetId") int targetId,
            @Param("targetType") String targetType
    );
}
