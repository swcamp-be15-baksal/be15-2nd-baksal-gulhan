package com.hanhwa_tae.gulhan.user.query.dto.response;

import com.hanhwa_tae.gulhan.user.query.dto.UserReviewDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserReviewResponse {
    private List<UserReviewDTO> userReviewList;
}
