package com.hanhwa_tae.gulhan.travelmatepost.query.mapper;

import com.hanhwa_tae.gulhan.travelmatepost.query.dto.response.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDTO> selectCommentByTravelMatePostId(int travelMatePostId);
}
