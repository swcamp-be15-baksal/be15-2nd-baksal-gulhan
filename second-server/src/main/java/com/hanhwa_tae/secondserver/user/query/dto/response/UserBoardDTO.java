package com.hanhwa_tae.secondserver.user.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardDTO {
    private String travelMatePostId;
    private String title;
    private String createdAt;
}
