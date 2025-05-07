package com.hanhwa_tae.secondserver.user.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentDTO {
    private String commentId;
    private String content;
    private String createdAt;
    private String travelMatePostId;
}
