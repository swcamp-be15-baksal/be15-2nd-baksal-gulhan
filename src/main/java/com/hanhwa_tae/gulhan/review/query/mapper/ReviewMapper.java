package com.hanhwa_tae.gulhan.review.query.mapper;


import com.hanhwa_tae.gulhan.review.query.dto.request.ReviewSearchRequest;
import com.hanhwa_tae.gulhan.review.query.dto.response.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewDTO> selectReview(ReviewSearchRequest request);
    long countReview(ReviewSearchRequest request);
}
